package com.nexushr.nexushr_server.common.context;

import java.util.UUID;

public class TenantContext {
    public static final ThreadLocal<UUID> CURRENT_TENANT = new ThreadLocal<>();

    public static void setCurrentTenant(UUID tenantId) {
        CURRENT_TENANT.set(tenantId);
    }

    public static UUID getCurrentTenant() {
        return CURRENT_TENANT.get();
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }
}
