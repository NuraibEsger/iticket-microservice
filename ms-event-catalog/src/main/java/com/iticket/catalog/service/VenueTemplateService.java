package com.iticket.catalog.service;

import com.iticket.catalog.dao.entity.SeatEntity;
import com.iticket.catalog.dao.entity.SectorEntity;
import com.iticket.catalog.dao.entity.VenueEntity;
import com.iticket.catalog.dao.repository.VenueRepository;
import com.iticket.catalog.model.requests.CreateVenueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueTemplateService {
    private final VenueRepository venueRepository;

    public Long createVenueTemplate(CreateVenueRequest request) {
        VenueEntity venue = VenueEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .capacity(request.getCapacity())
                .build();

        List<SectorEntity> sectors = request.getSectors().stream().map(sectorReq -> {
            SectorEntity sector = SectorEntity.builder()
                    .name(sectorReq.getName())
                    .venue(venue)
                    .build();

            List<SeatEntity> seats = sectorReq.getSeats().stream().map(seatReq ->
                    SeatEntity.builder()
                            .rowNumber(seatReq.getRowNo())
                            .seatNumber(seatReq.getSeatNo())
                            .sector(sector)
                            .build()
            ).collect(Collectors.toList());

            sector.setSeats(seats);
            return sector;
        }).toList();

        venue.setSectors(sectors);

        VenueEntity savedVenue = venueRepository.save(venue);

        return savedVenue.getId();
    }
}
