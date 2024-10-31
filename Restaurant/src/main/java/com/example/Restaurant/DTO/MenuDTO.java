package com.example.Restaurant.DTO;

public record MenuDTO(String Food,
            String Description,
            String Category,
            float Price,
            boolean Availability) {
}
