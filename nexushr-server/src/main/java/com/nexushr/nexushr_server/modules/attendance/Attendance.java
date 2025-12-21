package com.nexushr.nexushr_server.modules.attendance;

import jakarta.persistence.*;
import java.time.*;
import lombok.Getter;
import lombok.Setter;

import com.nexushr.nexushr_server.common.entity.BaseEntity;
import com.nexushr.nexushr_server.modules.employee.Employee;
import com.nexushr.nexushr_server.modules.tenant.Tenant;

@Entity
@Table(name = "attendance", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "tenant_id", "employee_id", "date" })
})
@Getter
@Setter
public class Attendance extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate date;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private String status;
}