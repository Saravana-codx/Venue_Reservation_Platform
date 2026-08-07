package com.hall.VenueMgmt.hall.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hall.VenueMgmt.hall.dto.HallRequest;
import com.hall.VenueMgmt.hall.entity.Hall;
import com.hall.VenueMgmt.hall.service.HallService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
@CrossOrigin
public class HallController {

    private final HallService hallService;

    // Only Users with OWNER or ADMIN role can create a venue
    @PostMapping
    @PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    public ResponseEntity<Hall> createHall(@RequestBody HallRequest request, Authentication authentication) {
        String ownerEmail = authentication.getPrincipal().toString();
        return ResponseEntity.ok(hallService.createHall(request, ownerEmail));
    }

    // Public endpoint to view all halls
    @GetMapping()
    public ResponseEntity<List<Hall>> getAllHalls() {
        return ResponseEntity.ok(hallService.getAllHalls());
    }

    // Only owners can view their specific halls
    @GetMapping("/my-halls")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<List<Hall>> getMyHalls(Authentication authentication) {
        String ownerEmail = authentication.getPrincipal().toString();
        return ResponseEntity.ok(hallService.getMyHalls(ownerEmail));
    }
}