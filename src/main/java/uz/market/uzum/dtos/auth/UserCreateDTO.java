package uz.market.uzum.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    ) String lastName, @NotBlank(
            message = "Email must not be blank"
    ) @Email(
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email is not valid"
    ) String email) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public @NotBlank(
            message = "Email must not be blank"
    ) @Email(
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email is not valid"
    ) String email() {
        return this.email;
    }
}
