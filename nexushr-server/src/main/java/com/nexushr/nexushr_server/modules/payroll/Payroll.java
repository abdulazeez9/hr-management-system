package com.nexushr.nexushr_server.modules.payroll;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.*;
import lombok.Getter;
import lombok.Setter;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.employee.Employee;
import com.nexushr.nexushr_server.modules.tenant.Tenant;

@Entity
@Table(name = "payroll")
@Getter
@Setter
public class Payroll extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate payPeriodStart;
    private LocalDate payPeriodEnd;

    @Column(precision = 10, scale = 2)
    private java.math.BigDecimal netSalary;

    private ZonedDateTime processedAt;
    private String status = "pending";
}