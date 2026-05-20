package com.iticket.catalog.dao.repository;

import com.iticket.catalog.dao.entity.TicketPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketPriceRepository extends JpaRepository<TicketPriceEntity, Long> {
    Optional<TicketPriceEntity> findByEventIdAndSectorId(Long id, Long sectorId);
}
