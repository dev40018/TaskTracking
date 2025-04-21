package com.example.tasktracking.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String detail
) {
}
