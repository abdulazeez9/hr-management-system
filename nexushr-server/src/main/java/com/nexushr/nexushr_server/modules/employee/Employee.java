package com.nexushr.nexushr_server.modules.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.department.Department;
import com.nexushr.nexushr_server.modules.tenant.Tenant;
import com.nexushr.nexushr_server.modules.user.User;

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

    @Transient
    private String sessionPerformanceNote;

    public Employee(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.baseSalary = salary;
    }

    public String getDisplayName() {
        return this.lastName.toUpperCase() + ", " + this.firstName;
    }

    public BigDecimal calculateAnnualBonus() {
        return this.baseSalary.multiply(new BigDecimal("0.10"));
    }
}
