package com.iticket.catalog.dao.repository;

import com.iticket.catalog.dao.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {
}
