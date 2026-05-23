package com.iticket.catalog.dao.repository;

import com.iticket.catalog.dao.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {
    Optional<EventEntity> findByVenueId(Long venueId);

    @Override
    Page<EventEntity> findAll(Pageable pageable);
}
