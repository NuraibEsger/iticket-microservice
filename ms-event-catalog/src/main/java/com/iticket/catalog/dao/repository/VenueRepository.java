package com.iticket.catalog.dao.repository;

import com.iticket.catalog.dao.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<VenueEntity, Long> {
    @Query("SELECT v from VenueEntity v LEFT JOIN FETCH v.sectors WHERE v.id = :id")
    Optional<VenueEntity> findByIdWithSectors(@Param("id") Long id);
}
