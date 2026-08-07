package com.hall.VenueMgmt.booking.service;

import java.util.List;

import com.hall.VenueMgmt.booking.dto.BookingRequest;
import com.hall.VenueMgmt.booking.dto.BookingResponse;

public interface BookingService {

    BookingResponse createBooking(BookingRequest request, String userEmail);

    List<BookingResponse> getMyBookings(String userEmail);

    List<BookingResponse> getOwnerBookings(String ownerEmail);

    BookingResponse cancelBooking(Long bookingId, String userEmail);
}