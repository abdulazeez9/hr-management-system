package com.nexushr.nexushr_server.modules.employee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.auth.User;
import com.nexushr.nexushr_server.modules.tenant.Tenant;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "base_salary", nullable = false)
    private BigDecimal baseSalary;

    private String status = "active";
}
