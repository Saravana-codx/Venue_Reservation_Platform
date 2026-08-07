package com.hall.VenueMgmt.booking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hall.VenueMgmt.booking.dto.BookingRequest;
import com.hall.VenueMgmt.booking.dto.BookingResponse;
import com.hall.VenueMgmt.booking.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    // 1. Create a new booking (Logged-in User/Customer)
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @RequestBody BookingRequest request, Principal principal) {
        return new ResponseEntity<>(
                bookingService.createBooking(request, principal.getName()), 
                HttpStatus.CREATED);
    }

    // 2. Get bookings made by the logged-in customer
    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingResponse>> getMyBookings(Principal principal) {
        return ResponseEntity.ok(bookingService.getMyBookings(principal.getName()));
    }

    // 3. Get all bookings for halls owned by the logged-in owner
    @GetMapping("/owner-bookings")
    @PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    public ResponseEntity<List<BookingResponse>> getOwnerBookings(Principal principal) {
        return ResponseEntity.ok(bookingService.getOwnerBookings(principal.getName()));
    }

    // 4. Cancel a booking
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(bookingService.cancelBooking(id, principal.getName()));
    }
}