package com.iticket.catalog.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    VENUE_NOT_FOUND("Venue not found with id: %d"),
    EVENT_NOT_FOUND("Event not found with id: %d"),
    INTERNAL_ERROR("An internal error occurred. Please try again later.");

    private final String message;
}
