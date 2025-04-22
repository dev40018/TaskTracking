package com.example.tasktracking.controller;

import com.example.tasktracking.domain.TaskList;
import com.example.tasktracking.domain.dto.TaskListDto;
import com.example.tasktracking.domain.mapper.TaskListMapper;
import com.example.tasktracking.service.TaskListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/task_list")
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
    @GetMapping("/{id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("id") UUID id){
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }
    @PutMapping("/{id}")
    public TaskListDto updateTaskList(
            @PathVariable("id") UUID taskListId,
            @RequestBody TaskListDto taskListDto){

        TaskList taskList = taskListMapper.fromDto(taskListDto);

        TaskList updateTaskList =  taskListService.updateTaskList(taskListId, taskList);
        return taskListMapper.toDto(updateTaskList);
    }
    @DeleteMapping("/{id}")
    public void deleteTaskListById(@PathVariable("id") UUID taskListId){
        taskListService.deleteTaskListById(taskListId);
    }

}
