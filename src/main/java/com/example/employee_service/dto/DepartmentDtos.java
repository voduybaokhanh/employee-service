package com.example.employee_service.dto;

import lombok.Data;

@Data
public class DepartmentDtos {
    @Data
    public static class CreateRequest {
        private String id;
        private String name;
    }
}


