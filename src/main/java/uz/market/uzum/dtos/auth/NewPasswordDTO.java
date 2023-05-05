package uz.market.uzum.dtos.auth;

public record NewPasswordDTO(
        String code,
        String password,
        String confirmPassword) {
}
