package com.eventbooking.eventservice.repository;

import com.eventbooking.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByVenue(String venue);
    List<Event> findByAvailableSeatsGreaterThan(Integer seats);
}
