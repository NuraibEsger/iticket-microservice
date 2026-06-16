package com.iticket.authentication.config;

import com.iticket.authentication.dao.entity.ERole;
import com.iticket.authentication.dao.entity.RoleEntity;
import com.iticket.authentication.dao.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeed implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            roleRepository.save(RoleEntity.builder().name(ERole.ROLE_USER).build());
        }
        if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(RoleEntity.builder().name(ERole.ROLE_ADMIN).build());
        }
    }
}
