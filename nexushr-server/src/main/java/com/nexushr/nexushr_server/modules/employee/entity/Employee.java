package com.nexushr.nexushr_server.modules.employee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.auth.User;
import com.nexushr.nexushr_server.modules.tenant.Tenant;

@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "tenant_id", "employee_code" })
})
@Getter
@Setter
public class Employee extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "employee_code", nullable = false, length = 50)
    private String employeeCode;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private String jobTitle;
    private LocalDate hireDate;
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String bankAccount;

    @Column(precision = 10, scale = 2, nullable = false)
    private java.math.BigDecimal baseSalary;

    private String status = "active";
}
