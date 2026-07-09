package com.awp.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.Instant;

@Builder
@Schema(description = "Response payload containing authentication tokens, metadata, and user info")
public record AuthResponse(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "Generated JWT access token string", example = "eyJhbGciOiJIUzI1NiJ9...")
        String token,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "The type of authentication token prefix", example = "Bearer")
        String tokenType,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        @Schema(description = "Server processing timestamp format formatted to Indian Standard Time", example = "05-Jul-2026 11:56 AM")
        Instant timestamp,

        @Schema(description = "Customized message detailing request success or feedback summary", example = "Authentication verification successful.")
        String message,

        @Schema(description = "The nested user profile metadata details block")
        UserSummaryDTO user) {
}