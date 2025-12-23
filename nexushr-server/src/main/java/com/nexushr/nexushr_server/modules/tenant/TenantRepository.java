package com.nexushr.nexushr_server.modules.tenant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexushr.nexushr_server.common.context.TenantContext;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    // Force all queries to include tenantId
    default List<Tenant> findAllForCurrentTenant() {
        UUID tenantId = TenantContext.getCurrentTenant();
        return findByTenantId(tenantId);
    }

    List<Tenant> findByTenantId(UUID tenantId);

    Optional<Tenant> findBySubdomain(String subdomain);

}