package com.example.tasktracking.domain.mapper;

import com.example.tasktracking.domain.Task;
import com.example.tasktracking.domain.TaskList;
import com.example.tasktracking.domain.dto.TaskDto;
import com.example.tasktracking.domain.dto.TaskListDto;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
