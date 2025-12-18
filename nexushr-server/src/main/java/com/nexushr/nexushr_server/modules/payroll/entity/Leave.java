package com.nexushr.nexushr_server.modules.payroll.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.auth.User;
import com.nexushr.nexushr_server.modules.employee.entity.Employee;
import com.nexushr.nexushr_server.modules.tenant.Tenant;

@Entity
@Table(name = "leaves")
@Getter
@Setter
public class Leave extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer days;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    private String status = "pending";
}