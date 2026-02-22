package com.eventbooking.bookingservice.repository;

import com.eventbooking.bookingservice.model.Booking;
import com.eventbooking.bookingservice.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByEventId(Long eventId);
    List<Booking> findByUserId(String userId);
    List<Booking> findByStatus(BookingStatus status);
}
