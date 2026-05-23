package com.iticket.catalog.mapper;

import com.iticket.catalog.dao.entity.SeatEntity;
import com.iticket.catalog.dao.entity.SectorEntity;
import com.iticket.catalog.dao.entity.VenueEntity;
import com.iticket.catalog.model.responses.SeatResponse;
import com.iticket.catalog.model.responses.SectorResponse;
import com.iticket.catalog.model.responses.VenueResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    /**
     * Maps the root Venue entity down to its response format.
     * MapStruct will automatically use the nested methods below to map
     * the List<SectorEntity> into List<SectorResponse>.
     */
    VenueResponse toResponse(VenueEntity entity);

    /**
     * Maps an individual Sector entity.
     * It will automatically trigger toSeatResponse for the nested seats list.
     */
    SectorResponse toSectorResponse(SectorEntity entity);

    /**
     * Maps an individual Seat entity.
     * Notice we don't map back to "sector" here to avoid an infinite loop.
     */
    SeatResponse toSeatResponse(SeatEntity entity);
}
