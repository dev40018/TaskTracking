package com.example.tasktracking.controller;

import com.example.tasktracking.domain.Task;
import com.example.tasktracking.domain.dto.TaskDto;
import com.example.tasktracking.domain.mapper.TaskMapper;
import com.example.tasktracking.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task_list/{task_list_id}/tasks")
public class TaskController {
    public final TaskService taskService;
    public final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTask(@PathVariable("task_list_id")UUID taskListId){
        return taskService.listTasks().stream().map(taskMapper::toDto).toList();
    }
    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto
    ){
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(createdTask);

    }

}
