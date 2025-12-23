package com.nexushr.nexushr_server.common.interceptor;

import java.util.UUID;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nexushr.nexushr_server.common.context.TenantContext;
import com.nexushr.nexushr_server.common.exception.TenantAccessException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) {
        String tenantHeader = request.getHeader("X-Tenant-ID");

        if (tenantHeader == null || tenantHeader.isBlank()) {
            throw new TenantAccessException("Missing Tenant Identification Header.");
        }

        TenantContext.setCurrentTenant(UUID.fromString(tenantHeader));
        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable Exception ex) throws Exception {
        TenantContext.clear();
    }
}