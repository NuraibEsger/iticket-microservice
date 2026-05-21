package com.iticket.catalog.controller;

import com.iticket.catalog.model.requests.CreateVenueRequest;
import com.iticket.catalog.service.VenueTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueTemplateService venueTemplateService;

    @PostMapping("/template")
    public ResponseEntity<Long> createVenueTemplate(@RequestBody CreateVenueRequest request)
    {
        Long venueId = venueTemplateService.createVenueTemplate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(venueId);
    }
}
