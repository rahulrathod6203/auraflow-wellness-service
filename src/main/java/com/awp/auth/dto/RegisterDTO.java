package com.awp.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Data payload required to register a new user account")
public record RegisterDTO(

        @NotBlank(message = "Name cannot be blank!")
        @Size(min = 3, message = "Name should have minimum 3 characters!")
        @Schema(description = "Full name of the user", example = "Rahul Rathod")
        String name,

        @NotBlank(message = "Email cannot be blank!")
        @Email(message = "Enter a valid email!")
        @Schema(description = "Unique email address for registration", example = "rahul@example.com")
        String email,

        @NotBlank(message = "Password cannot be blank!")
        @Size(min = 8, message = "Password should have minimum 8 characters!")
        @Schema(description = "Plain text password meeting security requirements", example = "Password@123!")
        String password,

        @NotBlank(message = "Phone cannot be blank!")
        @Size(min = 10, message = "Enter a valid phone number!")
        @Schema(description = "10-digit mobile phone number", example = "9876543210")
        String phone,

        @NotBlank(message = "Address cannot be blank!")
        @Size(min = 3, message = "Enter a valid address!")
        @Schema(description = "Residential or residential postal address", example = "Hinjawadi, Pune")
        String address

) {
}