package com.nexushr.nexushr_server.modules.user;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nexushr.nexushr_server.common.context.TenantContext;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getMyCompanyEmployees() {
        UUID currentTenantId = TenantContext.getCurrentTenant();
        if (currentTenantId == null)
            throw new RuntimeException("No tenant context found");

        return userRepository.findAllByTenantId(currentTenantId);
    }

    public User getEmployeeDetails(UUID userId) {
        return userRepository.findByIdAndTenantId(userId, TenantContext.getCurrentTenant())
                .orElseThrow(() -> new RuntimeException("User not found in your organization"));
    }

}
