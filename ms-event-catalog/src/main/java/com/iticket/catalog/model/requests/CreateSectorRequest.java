package com.iticket.catalog.model.requests;

import lombok.Data;

import java.util.List;

@Data
public class CreateSectorRequest {
    private String name;
    private List<CreateSeatRequest> seats;
}
