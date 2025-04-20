package com.example.tasktracking.domain.mapper.impl;

import com.example.tasktracking.domain.Task;
import com.example.tasktracking.domain.dto.TaskDto;
import com.example.tasktracking.domain.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.taskStatus(),
                taskDto.taskPriority(),
                null,
        null,
        null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
