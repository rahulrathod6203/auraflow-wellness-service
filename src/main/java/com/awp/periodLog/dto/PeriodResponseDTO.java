package com.awp.periodLog.dto;

import com.awp.periodLog.model.FlowIntensity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import java.time.Instant;
import java.time.LocalDate;

@Builder
public record PeriodResponseDTO(

        Long id,

        LocalDate startDate,

        LocalDate endDate,

        FlowIntensity flowIntensity,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a", timezone = "Asia/Kolkata")
        Instant updatedAt,

        Long userId

) {
}