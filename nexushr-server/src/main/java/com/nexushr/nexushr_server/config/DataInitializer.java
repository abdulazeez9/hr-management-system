package com.nexushr.nexushr_server.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.nexushr.nexushr_server.modules.tenant.Tenant;
import com.nexushr.nexushr_server.modules.tenant.TenantRepository;
import com.nexushr.nexushr_server.modules.user.User;
import com.nexushr.nexushr_server.modules.user.UserRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * DEV SEED: Runs when SPRING_PROFILES_ACTIVE=dev
     * Purpose: Create a sandbox environment for local testing.
     */
    @Bean
    @Profile("dev")
    CommandLineRunner initDevData() {
        return args -> {
            log.info("🧪 [DEV] Initializing sandbox data...");

            // 1. Ensure Dev Tenant exists
            Tenant devTenant = tenantRepository.findBySubdomain("dev-corp")
                    .orElseGet(() -> {
                        Tenant t = new Tenant();
                        t.setName("Development Corp");
                        t.setSubdomain("dev-corp");
                        log.info("🌱 [DEV] Created Dev Tenant");
                        return tenantRepository.save(t);
                    });

            // 2. Ensure Dev Admin exists
            if (userRepository.findByEmail("dev@nexushr.com").isEmpty()) {
                User user = new User();
                user.setTenant(devTenant);
                user.setEmail("dev@nexushr.com");
                user.setPassword(passwordEncoder.encode("nexushr!@2d"));
                user.setRole("SUPER_ADMIN");
                userRepository.save(user);
                log.info("✅ [DEV] Created Dev Admin: dev@nexushr.com");
            }
        };
    }

    /**
     * PROD SEED: Runs when SPRING_PROFILES_ACTIVE=prod
     * Purpose: Ensure the system has at least one tenant and one admin to allow
     * initial login.
     */
    @Bean
    @Profile("prod")
    CommandLineRunner initProdData() {
        return args -> {
            log.info("🚀 [PROD] Verifying system integrity...");

            // If the database is completely empty (fresh cloud deployment)
            if (tenantRepository.count() == 0) {
                log.info("🌟 [PROD] First-time setup detected. Creating Master Tenant...");

                Tenant masterTenant = new Tenant();
                masterTenant.setName("NexusHR Global");
                masterTenant.setSubdomain("admin-nexus");
                Tenant savedTenant = tenantRepository.save(masterTenant);

                User masterAdmin = new User();
                masterAdmin.setTenant(savedTenant);
                masterAdmin.setEmail("nexushr@gmail.com");
                masterAdmin.setPassword(passwordEncoder.encode("nexushr!@2d"));
                masterAdmin.setRole("SUPER_ADMIN");
                userRepository.save(masterAdmin);

                log.info("✅ [PROD] Master Admin created: nexushr@gmail.com");
                log.info("🔒 [PROD] Please change this password immediately after first login!");
            } else {
                log.info("ℹ️ [PROD] Database already contains records. Skipping auto-seed.");
            }
        };
    }
}