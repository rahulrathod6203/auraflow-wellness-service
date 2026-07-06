package com.awp.periodLog.service;

import com.awp.auth.exception.userDomain.PhoneAlreadyExistsException;
import com.awp.auth.exception.userDomain.UserNotFoundException;
import com.awp.auth.model.User;
import com.awp.auth.model.UserPrincipal;
import com.awp.auth.repository.UserRepository;
import com.awp.auth.service.CustomUserDetailsService;
import com.awp.periodLog.dto.PeriodRequestDTO;
import com.awp.periodLog.dto.PeriodResponseDTO;
import com.awp.periodLog.dto.PeriodResponsePage;
import com.awp.periodLog.mapper.PeriodLogMapper;
import com.awp.periodLog.model.PeriodLog;
import com.awp.periodLog.repository.PeriodLogRepository;
import com.awp.user.dto.UserRequestDTO;
import com.awp.user.dto.UserResponseDTO;
import com.awp.user.dto.UserResponsePage;
import com.awp.user.mapper.UserMapper;
import com.awp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PeriodLogServiceImpl implements PeriodLogService {

    private final PeriodLogRepository periodLogRepository;
    private final UserRepository userRepository;
    private final PeriodLogMapper mapper;

    @Override
    public PeriodResponseDTO savePeriodLog(Long userId, PeriodRequestDTO request) {

        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with the given id"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new RuntimeException("User is not authenticated");
        }

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        Long loggedUserId = user.getId();

        if (!loggedUserId.equals(userId)) {
            throw new AccessDeniedException("You are not authorized to modify another user's records.");
        }

        if (request.startDate().isAfter(request.endDate())) {
            throw new IllegalArgumentException("Period start date cannot be after the end date.");
        }

        PeriodLog periodLog = mapper.toEntity(request);

        PeriodLog savedPeriodLog = periodLogRepository.save(periodLog);

        return PeriodResponseDTO.builder()
                .startDate(savedPeriodLog.getStartDate())
                .endDate(savedPeriodLog.getEndDate())
                .flowIntensity(savedPeriodLog.getFlowIntensity())
                .createdAt(savedPeriodLog.getCreatedAt())
                .updatedAt(savedPeriodLog.getUpdatedAt())
                .userId(savedPeriodLog.getId())
                .build();

    }

    @Override
    public PeriodResponsePage getPeriodHistory(Long userId, int pageNo, int pageSize, String sortBy) {
        return null;
    }
}
