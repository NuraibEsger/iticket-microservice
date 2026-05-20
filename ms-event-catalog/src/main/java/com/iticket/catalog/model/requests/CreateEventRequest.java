package com.iticket.catalog.model.requests;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class CreateEventRequest {
    private String title;
    private String description;
    private LocalDateTime date;
    Long venueId;
    Map<Long, BigDecimal> sectorPrices;
}
