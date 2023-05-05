package uz.market.uzum.services.auth;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.market.uzum.configuration.jwt.JwtUtils;
import uz.market.uzum.domains.product.Basket;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.dtos.auth.RefreshTokenRequest;
import uz.market.uzum.dtos.auth.TokenRequest;
import uz.market.uzum.dtos.auth.TokenResponse;
import uz.market.uzum.dtos.auth.UserCreateDTO;
import uz.market.uzum.enums.TokenType;
import uz.market.uzum.enums.UserStatus;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.mappers.user.UserMapper;
import uz.market.uzum.repositories.BasketRepository;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.repositories.user.UserRolesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRolesRepository userRolesRepository;
    private final BasketRepository basketRepository;

    public TokenResponse generateToken(@NonNull TokenRequest tokenRequest) {
        String phoneNumber = tokenRequest.phoneNumber();
        String password = tokenRequest.password();
        this.userRepository.findByPhoneNumberLike(phoneNumber).orElseThrow(() -> new ItemNotFoundException("User not found"));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(phoneNumber, password);
        this.authenticationManager.authenticate(authentication);
        return this.jwtUtils.generateToken(phoneNumber);
    }

    public User create(@NotNull UserCreateDTO dto) {
        User user = UserMapper.INSTANCE.toEntity(dto);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(this.passwordEncoder.encode(dto.password()));
        user.setRoles(Collections.singletonList(this.userRolesRepository.findByCode("USER")));
        User savedUser = this.userRepository.save(user);
        return savedUser;
    }

    public TokenResponse refreshToken(@NotNull RefreshTokenRequest refreshTokenRequest) {

        String refreshToken = refreshTokenRequest.refreshToken();
        if (!this.jwtUtils.isTokenValid(refreshToken, TokenType.REFRESH))
            throw new CredentialsExpiredException("Token is invalid");

        String email = this.jwtUtils.getUsername(refreshToken, TokenType.REFRESH);
        this.userRepository.findByPhoneNumber(email);
        TokenResponse tokenResponse = TokenResponse.builder().refreshToken(refreshToken).refreshTokenExpiry(this.jwtUtils.getExpiry(refreshToken, TokenType.REFRESH)).build();
        return this.jwtUtils.generateAccessToken(email, tokenResponse);
    }


    public AuthService(
            final AuthenticationManager authenticationManager,
            final UserRepository userRepository,
            final PasswordEncoder passwordEncoder,
            final JwtUtils jwtUtils,
            final UserRolesRepository userRolesRepository,
            BasketRepository basketRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userRolesRepository = userRolesRepository;
        this.basketRepository = basketRepository;
    }
}
