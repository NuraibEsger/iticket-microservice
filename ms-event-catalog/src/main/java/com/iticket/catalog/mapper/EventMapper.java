package com.iticket.catalog.mapper;

import com.iticket.catalog.dao.entity.EventEntity;
import com.iticket.catalog.model.requests.CreateEventRequest;
import com.iticket.catalog.model.responses.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "venue", ignore = true)
    EventEntity toEntity(CreateEventRequest request);

    EventResponse toResponse(EventEntity entity);

    List<EventResponse> toResponse(List<EventEntity> entities);
}
