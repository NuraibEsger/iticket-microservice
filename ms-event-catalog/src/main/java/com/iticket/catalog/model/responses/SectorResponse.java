package com.iticket.catalog.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorResponse {
    private Long id;
    private String name;
    private List<SeatResponse> seats;
}
