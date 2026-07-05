package com.awp.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.Set;

@Builder
@Schema(description = "Summary details of an authenticated user profile")
public record UserSummaryDTO(

        @Schema(description = "Unique database identifier ID", example = "1")
        Long id,

        @Schema(description = "Full name of the user", example = "Rahul Rathod")
        String name,

        @Schema(description = "Unique email address profile identifier", example = "rahul@example.com")
        String email,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "Assigned security roles/permissions inside the system", example = "[\"USER\"]")
        Set<String> roles)
{}