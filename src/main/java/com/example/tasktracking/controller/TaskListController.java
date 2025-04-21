package com.example.tasktracking.controller;

import com.example.tasktracking.domain.TaskList;
import com.example.tasktracking.domain.dto.TaskListDto;
import com.example.tasktracking.domain.mapper.TaskListMapper;
import com.example.tasktracking.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task-list")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }
    @GetMapping
    public List<TaskListDto> getAllTasks(){
        List<TaskList> returnTaskList = taskListService.listTaskList();
        return returnTaskList.stream().map(taskListMapper::toDto).toList();
    }
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
       TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
       return taskListMapper.toDto(createdTaskList);

    }
}
