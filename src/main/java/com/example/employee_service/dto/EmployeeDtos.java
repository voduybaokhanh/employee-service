package com.example.employee_service.dto;

import lombok.Data;

@Data
public class EmployeeDtos {
    @Data
    public static class UpdateRequest {
        private String fullname;
        private String position;
        private Integer salary;
    }
}


