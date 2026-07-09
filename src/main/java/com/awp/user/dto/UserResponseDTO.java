package com.awp.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
@Schema(description = "Detailed profile information returned for user operations")
public record UserResponseDTO(

        @Schema(description = "Unique database identifier ID", example = "1")
        Long id,

        @Schema(description = "Full name of the user", example = "Rahul Rathod")
        String name,

        @Schema(description = "Unique email address profile identifier", example = "rahul@example.com")
        String email,

        @Schema(description = "10-digit mobile phone number", example = "9876543210")
        String phone,

        @Schema(description = "Residential or office address details", example = "Hinjawadi, Pune")
        String address,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        @Schema(description = "Account creation timestamp profile setting", example = "05-Jul-2026 11:58 AM")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        @Schema(description = "Last profile modification timestamp setting", example = "05-Jul-2026 11:58 AM")
        Instant updatedAt,

        @Schema(description = "Account status indicating if the user profile is active", example = "true")
        Boolean active,

        @Schema(description = "Assigned security roles/permissions inside the system", example = "[\"USER\"]")
        Set<String> roles
) {
}