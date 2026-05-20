package com.iticket.catalog.model.requests;

import java.math.BigDecimal;

public record PriceRequest(
        Long sectorId,
        BigDecimal price
) {}
