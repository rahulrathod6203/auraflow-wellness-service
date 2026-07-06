package com.awp.periodLog.service;

import com.awp.periodLog.dto.PeriodRequestDTO;
import com.awp.periodLog.dto.PeriodResponseDTO;
import com.awp.periodLog.dto.PeriodResponsePage;
import com.awp.user.dto.UserRequestDTO;
import com.awp.user.dto.UserResponseDTO;
import com.awp.user.dto.UserResponsePage;

import java.util.List;

public interface PeriodLogService {

    PeriodResponseDTO savePeriodLog(Long userId, PeriodRequestDTO request);

    PeriodResponsePage getPeriodHistory(Long userId, int pageNo, int pageSize, String sortBy);

}
