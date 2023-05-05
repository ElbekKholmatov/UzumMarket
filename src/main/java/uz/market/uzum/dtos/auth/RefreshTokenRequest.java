package uz.market.uzum.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import org.springdoc.core.annotations.ParameterObject;

@ParameterObject
public record RefreshTokenRequest(
        @NotBlank String refreshToken) {
}
