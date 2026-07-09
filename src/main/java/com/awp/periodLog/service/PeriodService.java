package com.awp.periodLog.service;

import com.awp.periodLog.dto.PeriodRequestDTO;
import com.awp.periodLog.dto.PeriodResponseDTO;
import com.awp.periodLog.dto.PeriodResponsePage;

public interface PeriodService {

    PeriodResponseDTO savePeriodLog(Long userId, PeriodRequestDTO request);

    PeriodResponsePage getPeriodHistory(Long userId, int pageNo, int pageSize, String sortBy);

}
