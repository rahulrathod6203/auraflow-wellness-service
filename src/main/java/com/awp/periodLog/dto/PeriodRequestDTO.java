package com.awp.periodLog.dto;

import com.awp.periodLog.model.FlowIntensity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PeriodRequestDTO(

        @NotNull(message = "Start date is required!")
        @PastOrPresent(message = "Start date cannot be in the future!")
        LocalDate startDate,

        @NotNull(message = "End date is required!")
        LocalDate endDate,

        @NotNull(message = "Flow intensity is required!")
        FlowIntensity flowIntensity

) {
}