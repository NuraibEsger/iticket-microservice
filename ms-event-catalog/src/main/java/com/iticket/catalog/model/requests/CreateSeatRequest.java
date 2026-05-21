package com.iticket.catalog.model.requests;

import lombok.Data;

@Data
public class CreateSeatRequest {
    private Integer rowNo;
    private Integer seatNo;
}
