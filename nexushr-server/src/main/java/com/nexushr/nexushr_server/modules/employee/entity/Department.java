package com.nexushr.nexushr_server.modules.employee.entity;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.auth.User;
import com.nexushr.nexushr_server.modules.tenant.Tenant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter

public class Department extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;
}