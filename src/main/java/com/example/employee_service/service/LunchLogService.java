package com.example.employee_service.service;

import com.example.employee_service.dto.LunchLogDtos;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.entity.LunchLog;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.repository.LunchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LunchLogService {
    private final LunchLogRepository lunchLogRepository;
    private final EmployeeRepository employeeRepository;

    // Bulk insert lunch logs; skip invalid employees
    public List<LunchLog> bulkCreate(LunchLogDtos.BulkCreateRequest request) {
        List<LunchLog> toSave = new ArrayList<>();
        for (LunchLogDtos.BulkCreateRequest.Item item : request.getItems()) {
            Employee employee = employeeRepository.findById(item.getEmployeeId()).orElse(null);
            if (employee == null) {
                // Business rule: ignore entries with invalid employee_id
                continue;
            }
            LunchLog log = new LunchLog();
            log.setEmployee(employee);
            log.setDate(item.getDate());
            log.setMenu(item.getMenu());
            log.setCreatedAt(LocalDateTime.now());
            toSave.add(log);
        }
        return lunchLogRepository.saveAll(toSave);
    }
}


