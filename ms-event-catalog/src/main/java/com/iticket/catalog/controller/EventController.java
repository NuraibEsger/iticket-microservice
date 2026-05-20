package com.iticket.catalog.controller;

import com.iticket.catalog.model.requests.CreateEventRequest;
import com.iticket.catalog.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Long> createEvent(@RequestBody CreateEventRequest request){
        Long eventId = eventService.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventId);
    }
}
