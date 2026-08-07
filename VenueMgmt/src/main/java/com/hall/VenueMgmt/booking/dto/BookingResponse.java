package com.hall.VenueMgmt.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.hall.VenueMgmt.booking.entity.Booking.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long id;
    private Long hallId;
    private String hallName;
    private String hallLocation;
    private String userEmail;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    private BookingStatus status;
}