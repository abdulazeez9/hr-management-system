package com.nexushr.nexushr_server.common.interceptor;

import java.util.UUID;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nexushr.nexushr_server.common.context.TenantContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) throws Exception {
        String tenantHeader = request.getHeader("X-Tenant-ID");

        if (tenantHeader != null && !tenantHeader.isBlank()) {
            try {
                UUID tenantId = UUID.fromString(tenantHeader);
                TenantContext.setCurrentTenant(tenantId);
                log.debug("Tenant context set: {}", tenantId);
            } catch (IllegalArgumentException e) {
                log.warn("Invalid tenant ID format: {}", tenantHeader);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tenant ID");
                return false;
            }
        } else {
            log.warn("No tenant ID provided in request");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tenant ID required");
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable Exception ex) throws Exception {
        TenantContext.clear();
        log.debug("Tenant context cleared");
    }
}