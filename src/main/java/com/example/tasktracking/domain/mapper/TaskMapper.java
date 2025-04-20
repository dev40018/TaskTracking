package com.example.tasktracking.domain.mapper;

import com.example.tasktracking.domain.Task;
import com.example.tasktracking.domain.dto.TaskDto;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);

}
