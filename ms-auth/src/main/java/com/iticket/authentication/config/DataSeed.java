package com.iticket.authentication.config;

import com.iticket.authentication.dao.entity.ERole;
import com.iticket.authentication.dao.entity.UserEntity;
import com.iticket.authentication.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeed implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed.admin.username}")
    private String adminUsername;
    @Value("${app.seed.admin.email}")
    private String adminEmail;
    @Value("${app.seed.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        seedAdminUser();
    }

    private void seedAdminUser() {
        if (userRepository.existsByUsername(adminUsername)) {
            log.info("Admin Username already exists in database.");
            return;
        }

        UserEntity userEntity = UserEntity.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .email(adminEmail)
                .active(true)
                .roles(Set.of(ERole.ROLE_ADMIN, ERole.ROLE_USER))
                .build();

        userRepository.save(userEntity);
        log.info("Admin Username has been created successfully.");
    }
}
