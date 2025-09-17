package com.example.employee_service.entity;

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
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    private String status; // e.g., NEW, ONGOING, DONE

    @Column(name = "due_date")
    private java.time.LocalDate dueDate;

    @Column(name = "assigned_by")
    private String assignedBy; // employee id who assigned

    // Note: created_at and updated_at not in SQL schema, removed
}


