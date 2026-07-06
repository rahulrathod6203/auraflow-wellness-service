package com.awp.periodLog.service;

import com.awp.auth.exception.userDomain.UserNotFoundException;
import com.awp.auth.model.User;
import com.awp.auth.model.UserPrincipal;
import com.awp.auth.repository.UserRepository;
import com.awp.periodLog.dto.PeriodRequestDTO;
import com.awp.periodLog.dto.PeriodResponseDTO;
import com.awp.periodLog.dto.PeriodResponsePage;
import com.awp.periodLog.mapper.PeriodMapper;
import com.awp.periodLog.model.Period;
import com.awp.periodLog.repository.PeriodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final UserRepository userRepository;
    private final PeriodMapper mapper;

    @Transactional
    @Override
    public PeriodResponseDTO savePeriodLog(Long userId, PeriodRequestDTO request) {
        log.info("Inside service layer at save period log");
        User loggedUserDetails = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with the given id"));

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

        Period period = mapper.toEntity(request);
        period.setUser(loggedUserDetails);

        Period savedPeriod = periodRepository.save(period);

        log.info("Period ID is, {}", savedPeriod.getId());
        return PeriodResponseDTO.builder()
                .id(savedPeriod.getId())
                .startDate(savedPeriod.getStartDate())
                .endDate(savedPeriod.getEndDate())
                .flowIntensity(savedPeriod.getFlowIntensity())
                .createdAt(savedPeriod.getCreatedAt())
                .updatedAt(savedPeriod.getUpdatedAt())
                .userId(userId)
                .build();

    }

    @Override
    public PeriodResponsePage getPeriodHistory(Long userId, int pageNo, int pageSize, String sortBy) {





        return null;
    }
}
