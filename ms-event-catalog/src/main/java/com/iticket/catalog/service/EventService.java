package com.iticket.catalog.service;

import com.iticket.catalog.dao.entity.EventEntity;
import com.iticket.catalog.dao.entity.TicketPriceEntity;
import com.iticket.catalog.dao.entity.VenueEntity;
import com.iticket.catalog.dao.repository.EventRepository;
import com.iticket.catalog.dao.repository.TicketPriceRepository;
import com.iticket.catalog.dao.repository.VenueRepository;
import com.iticket.catalog.mapper.EventMapper;
import com.iticket.catalog.model.requests.CreateEventRequest;
import com.iticket.catalog.model.responses.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final TicketPriceRepository ticketPriceRepository;
    private final EventMapper eventMapper;

    @Transactional
    public Long createEvent(CreateEventRequest request) {
        VenueEntity venue = venueRepository.findByIdWithSectors(request.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found with id " + request.getVenueId()));

        EventEntity eventEntity = EventEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .eventDate(request.getDate())
                .venue(venue)
                .build();

        EventEntity savedEvent = eventRepository.save(eventEntity);

        venue.getSectors().forEach(sector -> {
            BigDecimal price = request.getPrices().get(sector.getId());
            if (price == null) {
                throw new RuntimeException(sector.getName() + " not found");
            }

            TicketPriceEntity ticketPrice = TicketPriceEntity.builder()
                    .event(savedEvent)
                    .sector(sector)
                    .price(price)
                    .build();

            ticketPriceRepository.save(ticketPrice);
        });

        return savedEvent.getId();
    }

    public Page<EventResponse> findAll(Pageable pageable) {
        var entities =  eventRepository.findAll(pageable);

        return new PageImpl<>(eventMapper.toResponse(entities.getContent()), pageable, entities.getTotalElements());
    }

    public EventResponse findByVenueId(Long venueId) {
        EventEntity event = eventRepository.findByVenueId(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found with id " + venueId));

        return eventMapper.toResponse(event);
    }

    public EventResponse findById(Long eventId) {

        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + eventId));

        return eventMapper.toResponse(event);
    }
}
