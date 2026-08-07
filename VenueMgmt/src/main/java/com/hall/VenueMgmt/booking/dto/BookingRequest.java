package com.hall.VenueMgmt.booking.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private Long hallId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}