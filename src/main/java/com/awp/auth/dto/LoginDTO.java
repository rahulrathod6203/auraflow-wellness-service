package com.awp.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Data payload required to log a user into the application")
public record LoginDTO(

        @NotBlank(message = "Email cannot be blank!")
        @Email(message = "Enter a valid email!")
        @Schema(description = "Registered email address of the user", example = "rahul@example.com")
        String email,

        @NotBlank(message = "Password cannot be blank!")
        @Size(min = 8, message = "Invalid password!")
        @Schema(description = "Account password text", example = "Password@123!")
        String password
) {
}