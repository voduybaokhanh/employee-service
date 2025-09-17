package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDtos;
import com.example.employee_service.entity.Department;
import com.example.employee_service.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    // Return all departments
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    // Create department with specified ID and name
    public Department create(DepartmentDtos.CreateRequest request) {
        Department dept = new Department();
        dept.setId(request.getId());
        dept.setName(request.getName());
        return departmentRepository.save(dept);
    }
}


