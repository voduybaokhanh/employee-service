package com.example.employee_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class LunchLogDtos {
    @Data
    public static class BulkCreateRequest {
        private List<Item> items;

        @Data
        public static class Item {
            private String employeeId;
            private LocalDate lunchDate;
            private String mealType;
            private String restaurant;
            private String notes;
        }
    }
}


