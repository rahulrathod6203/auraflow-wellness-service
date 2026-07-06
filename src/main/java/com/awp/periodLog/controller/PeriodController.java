package com.awp.periodLog.controller;

import com.awp.user.dto.UserRequestDTO;
import com.awp.user.dto.UserResponseDTO;
import com.awp.user.dto.UserResponsePage;
import com.awp.user.service.UserService;
import com.awp.user.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/periods")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PeriodController {

    private final UserService userService;


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

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and principal.id == #id)")
    @Operation(summary = "Get user profile by ID", description = "Fetches a single user record using their unique database ID. Accessible by ADMINs or the profile owner.")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and principal.id == #id)")
    @Operation(summary = "Update user profile details", description = "Modifies existing profile properties like name, phone, or address. Accessible by ADMINs or the profile owner.")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.accepted().body(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete user account by ID", description = "Permanently removes a user identity record from the system. Only accessible by ADMIN users.")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        String deletedMessage = userService.deleteUserById(id);
        return ResponseEntity.ok().body(deletedMessage);
    }
}