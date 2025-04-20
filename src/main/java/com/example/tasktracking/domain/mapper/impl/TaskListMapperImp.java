package com.example.tasktracking.domain.mapper.impl;

import com.example.tasktracking.domain.Task;
import com.example.tasktracking.domain.TaskList;
import com.example.tasktracking.domain.TaskStatus;
import com.example.tasktracking.domain.dto.TaskListDto;
import com.example.tasktracking.domain.mapper.TaskListMapper;
import com.example.tasktracking.domain.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImp implements TaskListMapper {
    private final TaskMapper taskMapper;

    public TaskListMapperImp(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                        taskListDto.id(),
                        taskListDto.title(),
                        taskListDto.description(),
                null,
                null,
                Optional.ofNullable(taskListDto.tasks())
                                .map(tasks -> tasks.stream().map(taskMapper::fromDto).toList()).orElse(null)
                );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)
        );


    }
    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks) {
            return null;
        }
        long closedTasksCount =
        tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();
        return (double) (closedTasksCount / tasks.size());
    }
}
