package uz.market.uzum.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.services.user.UserService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "This API is used for getting user by id",
            description = "This API is used for getting user by id", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping(value = "{userId}", produces = "application/json")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "This API is used for getting all users",
            description = "This API is used for getting all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping(value = "/all")
    public Collection<User> getAllUsers() {
        return userService.findAllUser();
    }


}


