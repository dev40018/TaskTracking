package com.example.tasktracking.service.impl;

import com.example.tasktracking.domain.TaskList;
import com.example.tasktracking.domain.mapper.TaskListMapper;
import com.example.tasktracking.repository.TaskListRepository;
import com.example.tasktracking.service.TaskListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(taskList.getId() != null){
            throw new  IllegalArgumentException("TaskList Already has an Id");
        }
        if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("TaskList Title Must be Present");
        }
        return taskListRepository.save(new TaskList(
                        null,
                        taskList.getTitle(),
                        taskList.getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
                ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID taskListId) {
        return taskListRepository.findById(taskListId);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(taskListId == null){
            throw new IllegalArgumentException("Task List Must have and ID");
        }


        /*
        Optional<TaskList> taskListById = taskListRepository.findById(taskListId);
        if(taskListById.isEmpty()){
            throw new IllegalArgumentException("couldn't find the TaskList");
        }*/
        TaskList existingTaskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("TaskList Not Found"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdatedAt(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskListById(UUID id) {
        taskListRepository.deleteById(id);
    }

}
