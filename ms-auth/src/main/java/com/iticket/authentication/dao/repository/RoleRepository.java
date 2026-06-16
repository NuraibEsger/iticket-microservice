package com.iticket.authentication.dao.repository;

import com.iticket.authentication.dao.entity.ERole;
import com.iticket.authentication.dao.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByName(ERole name);
}
