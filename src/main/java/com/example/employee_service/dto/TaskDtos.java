package com.example.employee_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskDtos {
    @Data
    public static class CreateRequest {
        // Accept snake_case fields as per API contract
        @JsonProperty("employee_id")
        private String employeeId;

        @JsonProperty("task_name")
        private String taskName;

        private String description;
        private String status;
        
        @JsonProperty("due_date")
        private java.time.LocalDate dueDate;
    }
}


