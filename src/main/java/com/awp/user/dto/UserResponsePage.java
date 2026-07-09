package com.awp.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "Paginated wrapper containing a list of user profiles along with navigation metadata")
public record UserResponsePage(

        @Schema(description = "List of user details returned for the current page slice")
        List<UserResponseDTO> content,

        @Schema(description = "The current page number index (starts at 0)", example = "0")
        int pageNo,

        @Schema(description = "The maximum number of records requested per page slice", example = "10")
        int pageSize,

        @Schema(description = "Total number of pages available based on current page size limits", example = "5")
        int totalPages,

        @Schema(description = "Flag indicating if the current slice is the absolute final page split available", example = "false")
        boolean lastPage
) {
}