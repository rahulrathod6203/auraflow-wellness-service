package com.awp.periodLog.dto;

import com.awp.periodLog.model.Period;
import lombok.Builder;

import java.util.List;

@Builder
public record PeriodResponsePage(

        List<Period> content,

        int pageNo,

        int pageSize,

        int totalPages,

        boolean lastPage
) {
}