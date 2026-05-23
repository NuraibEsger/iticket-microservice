package com.iticket.catalog.controller;

import com.iticket.catalog.dao.entity.EventEntity;
import com.iticket.catalog.dao.entity.VenueEntity;
import com.iticket.catalog.model.requests.CreateEventRequest;
import com.iticket.catalog.model.responses.EventResponse;
import com.iticket.catalog.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Long> createEvent(@RequestBody CreateEventRequest request){
        Long eventId = eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventId);
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<EventResponse> findVenueByVenueId(@PathVariable Long venueId){
        EventResponse venue = eventService.findByVenueId(venueId);

        return ResponseEntity.status(HttpStatus.OK).body(venue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findEventById(@PathVariable Long id){
        EventResponse event = eventService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }
}
