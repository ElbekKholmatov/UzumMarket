package uz.market.uzum.dtos.auth;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springdoc.core.annotations.ParameterObject;

import java.io.Serializable;

@ParameterObject
public record UserCreateDTO(@Size(
        min = 8,
        max = 20,
        message = "length of password must be between 8 and 20"
) @NotBlank(
        message = "password must not be blank"
) String password, @NotBlank(
        message = "Firstname must not be blank"
) String firstName, @NotBlank(
        message = "Lastname must not be blank"
) String lastName, @NotBlank(
        message = "Phone number must not be blank"
) @Pattern(
        regexp = "^(\\+998|8)[ -]?\\d{2}[ -]?\\d{3}[ -]?\\d{2}[ -]?\\d{2}$"
) String phoneNumber, @NotBlank(
        message = "Email must not be blank"
) @Email(
        regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
        message = "Email is not valid"
) String email) implements Serializable {
    public UserCreateDTO(@Size(
            min = 8,
            max = 20,
            message = "length of password must be between 8 and 20"
    ) @NotBlank(
            message = "password must not be blank"
    ) String password, @NotBlank(
            message = "Firstname must not be blank"
    ) String firstName, @NotBlank(
            message = "Lastname must not be blank"
    ) String lastName, @Parameter(description = "Phone number must be in format +998 99 999 99 or  8 99 999 99 or with hyphen") @NotBlank(
            message = "Phone number must not be blank"
    ) @Pattern(
            regexp = "^(\\+998|8)[ -]?\\d{2}[ -]?\\d{3}[ -]?\\d{2}[ -]?\\d{2}$"
    ) String phoneNumber, @NotBlank(
            message = "Email must not be blank"
    ) @Email(
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email is not valid"
    ) String email) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public @Size(
            min = 8,
            max = 20,
            message = "length of password must be between 8 and 20"
    ) @NotBlank(
            message = "password must not be blank"
    ) String password() {
        return this.password;
    }

    public @NotBlank(
            message = "Firstname must not be blank"
    ) String firstName() {
        return this.firstName;
    }

    public @NotBlank(
            message = "Lastname must not be blank"
    ) String lastName() {
        return this.lastName;
    }

    @Parameter(
            description = "Phone number must be in format +998 99 999 99 or  8 99 999 99 or with hyphen"
    )
    public @NotBlank(
            message = "Phone number must not be blank"
    ) @Pattern(
            regexp = "^(\\+998|8)[ -]?\\d{2}[ -]?\\d{3}[ -]?\\d{2}[ -]?\\d{2}$"
    ) String phoneNumber() {
        return this.phoneNumber;
    }

    public @NotBlank(
            message = "Email must not be blank"
    ) @Email(
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email is not valid"
    ) String email() {
        return this.email;
    }
}
