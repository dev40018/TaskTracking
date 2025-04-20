package com.example.tasktracking.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer tasksCount,
        Double progress, // num between 0 and 1 how many tasks in our task list are completed
        List<TaskDto> tasks
) {
}