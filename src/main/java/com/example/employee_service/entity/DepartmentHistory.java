package com.example.employee_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "old_department_id")
    private String oldDepartmentId;

    @Column(name = "new_department_id", nullable = false)
    private String newDepartmentId;

    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeDate;
}


