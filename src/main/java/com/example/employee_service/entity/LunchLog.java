package com.example.employee_service.entity;

import java.time.LocalDate;
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
@Table(name = "lunch_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "lunch_date", nullable = false)
    private LocalDate lunchDate;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "restaurant")
    private String restaurant;

    @Column(name = "notes")
    private String notes;
}


