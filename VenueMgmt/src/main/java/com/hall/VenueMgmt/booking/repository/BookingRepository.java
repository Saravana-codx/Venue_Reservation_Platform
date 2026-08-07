package com.hall.VenueMgmt.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hall.VenueMgmt.booking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find all bookings made by a specific customer
    List<Booking> findByUserEmail(String email);

    // Find all bookings for halls owned by a specific owner
    List<Booking> findByHallOwnerEmail(String email);

    // Custom JPQL Query: Checks if the hall is ALREADY booked for overlapping dates
    @Query("SELECT COUNT(b) > 0 FROM Booking b " +
           "WHERE b.hall.id = :hallId " +
           "AND b.status != 'CANCELLED' " +
           "AND (:checkInDate < b.checkOutDate AND :checkOutDate > b.checkInDate)")
    boolean existsOverlappingBooking(@Param("hallId") Long hallId,
                                     @Param("checkInDate") LocalDate checkInDate,
                                     @Param("checkOutDate") LocalDate checkOutDate);
}