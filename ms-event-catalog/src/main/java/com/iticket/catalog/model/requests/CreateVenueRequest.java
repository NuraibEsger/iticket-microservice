package com.iticket.catalog.model.requests;

import lombok.Data;

import java.util.List;

@Data
public class CreateVenueRequest {
    private String name;
    private String address;
    Integer capacity;
    private List<CreateSectorRequest> sectors;
}
