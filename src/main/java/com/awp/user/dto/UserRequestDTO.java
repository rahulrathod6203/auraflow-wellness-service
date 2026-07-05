package com.awp.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Data payload required to update a user's profile details")
public record UserRequestDTO(

        @NotBlank(message = "Name cannot be blank!")
        @Schema(description = "Full name of the user", example = "Rahul Rathod")
        String name,

        /*
        @NotBlank(message = "Email cannot be blank!")
        @Email(message = "Enter a valid email!")
        String email,

        @NotBlank(message = "Password cannot be blank!")
        @Size(min = 8, message = "Password should have minimum 8 characters!")
        String password,
        */

        @NotBlank(message = "Phone cannot be blank!")
        @Size(min = 10, message = "Enter a valid phone number!")
        @Schema(description = "10-digit mobile phone number", example = "9876543210")
        String phone,

        @NotBlank(message = "Address cannot be blank!")
        @Size(min = 5, message = "Enter a valid address!!")
        @Schema(description = "Residential or office address details", example = "Hinjawadi, Pune")
        String address

) {
}