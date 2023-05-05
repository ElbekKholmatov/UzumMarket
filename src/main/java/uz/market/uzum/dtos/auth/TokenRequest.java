package uz.market.uzum.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record TokenRequest(@NotBlank String phoneNumber, @NotBlank String password) {
}
