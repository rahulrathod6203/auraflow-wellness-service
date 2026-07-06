package com.awp.periodLog.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PeriodResponsePage(

        List<PeriodResponseDTO> content,

        int pageNo,

        int pageSize,

        int totalPages,

        boolean lastPage
) {
}