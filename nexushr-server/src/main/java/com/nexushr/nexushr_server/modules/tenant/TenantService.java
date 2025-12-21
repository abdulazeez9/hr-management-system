package com.nexushr.nexushr_server.modules.tenant;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    public Tenant getTenantBySubDomain(String subdomain) {
        return tenantRepository.findBySubdomain(subdomain)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }
}
