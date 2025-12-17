package com.nexushr.nexushr_server.modules.attendance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.employee.entity.Employee;
import com.nexushr.nexushr_server.modules.tenant.Tenant;

@Entity
@Table(name = "attendance")
@Getter
@Setter
public class Attendance extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "clock_in")
    private LocalTime clockIn;

    @Column(name = "clock_out")
    private LocalTime clockOut;

    private String status = "present";

    @Column(columnDefinition = "TEXT")
    private String notes;
}