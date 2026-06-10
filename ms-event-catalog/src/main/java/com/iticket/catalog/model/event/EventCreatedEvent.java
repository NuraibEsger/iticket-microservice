package com.iticket.catalog.model.event;

import java.time.LocalDateTime;

public record EventCreatedEvent(Long eventId, String title, LocalDateTime date, Long venueId) { }
