package com.awp.periodLog.controller;

import com.awp.periodLog.dto.PeriodRequestDTO;
import com.awp.periodLog.dto.PeriodResponseDTO;
import com.awp.periodLog.service.PeriodService;
import com.awp.user.dto.UserRequestDTO;
import com.awp.user.dto.UserResponseDTO;
import com.awp.user.dto.UserResponsePage;
import com.awp.user.service.UserService;
import com.awp.user.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/period")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class PeriodController {

    private final UserService userService;

    private final PeriodService periodService;

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and principal.id == #userId)")
    @PostMapping("/create/{userId}")
    public ResponseEntity<PeriodResponseDTO> savePeriodLog(@PathVariable Long userId,
                                                           @Valid @RequestBody PeriodRequestDTO request){

        log.info("saving period log");
        PeriodResponseDTO periodResponseDTO = periodService.savePeriodLog(userId, request);

        URI location= ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{userId}")
                        .buildAndExpand(userId)
                        .toUri();

        log.info(String.valueOf(periodResponseDTO));

        return ResponseEntity.created(location).body(periodResponseDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users (Paginated)", description = "Fetches a paginated list of all registered users. Only accessible by ADMIN users.")
    public ResponseEntity<UserResponsePage> getAllUsers(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NO) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    ) {
        return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize, sortBy));
    }

}