package com.example.tasktracking.domain.dto;

import com.example.tasktracking.domain.TaskPriority;
import com.example.tasktracking.domain.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority taskPriority,
        TaskStatus taskStatus
) {
}