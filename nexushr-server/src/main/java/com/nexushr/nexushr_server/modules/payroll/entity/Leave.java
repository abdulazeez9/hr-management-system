package com.nexushr.nexushr_server.modules.payroll.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "leave_type", nullable = false)
    private String leaveType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Integer days;

    @Column(columnDefinition = "TEXT")
    private String reason;

    private String status = "pending";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;
}