package com.example.employee_service.dto;

import lombok.Data;

@Data
public class TaskDtos {
    @Data
    public static class CreateRequest {
        private String employeeId;
        private String taskName;
        private String description;
    }
}


