package com.hall.VenueMgmt.booking.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hall.VenueMgmt.booking.dto.BookingRequest;
import com.hall.VenueMgmt.booking.dto.BookingResponse;
import com.hall.VenueMgmt.booking.entity.Booking;
import com.hall.VenueMgmt.booking.entity.Booking.BookingStatus;
import com.hall.VenueMgmt.booking.repository.BookingRepository;
import com.hall.VenueMgmt.exception.ResourceAlreadyExistsException;
import com.hall.VenueMgmt.hall.entity.Hall;
import com.hall.VenueMgmt.hall.repository.HallRepository;
import com.hall.VenueMgmt.user.entity.User;
import com.hall.VenueMgmt.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HallRepository hallRepository;
    private final UserRepository userRepository;

    @Override
    public BookingResponse createBooking(BookingRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Hall hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found"));

        if (request.getCheckInDate().isAfter(request.getCheckOutDate()) || 
            request.getCheckInDate().isEqual(request.getCheckOutDate())) {
            throw new RuntimeException("Check-out date must be after check-in date");
        }

        // Check for overlapping bookings
        boolean isOverlapping = bookingRepository.existsOverlappingBooking(
                hall.getId(), request.getCheckInDate(), request.getCheckOutDate());

     // Inside createBooking method in BookingServiceImpl.java
        if (isOverlapping) {
            throw new ResourceAlreadyExistsException("Hall is already booked for the selected dates!");
        }

        // Calculate total price based on number of days booked
        long days = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        BigDecimal totalPrice = hall.getPricePerDay().multiply(BigDecimal.valueOf(days));

        Booking booking = Booking.builder()
                .hall(hall)
                .user(user)
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .build();

        Booking savedBooking = bookingRepository.save(booking);
        return mapToResponse(savedBooking);
    }

    @Override
    public List<BookingResponse> getMyBookings(String userEmail) {
        return bookingRepository.findByUserEmail(userEmail)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getOwnerBookings(String ownerEmail) {
        return bookingRepository.findByHallOwnerEmail(ownerEmail)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse cancelBooking(Long bookingId, String userEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You are not authorized to cancel this booking");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        Booking updatedBooking = bookingRepository.save(booking);
        return mapToResponse(updatedBooking);
    }

    private BookingResponse mapToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .hallId(booking.getHall().getId())
                .hallName(booking.getHall().getName())
                .hallLocation(booking.getHall().getLocation())
                .userEmail(booking.getUser().getEmail())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .totalPrice(booking.getTotalPrice())
                .status(booking.getStatus())
                .build();
    }
}