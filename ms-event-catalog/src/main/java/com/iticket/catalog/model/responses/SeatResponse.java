package com.iticket.catalog.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse {
    private Long id;
    private Integer rowNumber;
    private Integer seatNumber;
    private Integer coordinateX;
    private Integer coordinateY;
}
