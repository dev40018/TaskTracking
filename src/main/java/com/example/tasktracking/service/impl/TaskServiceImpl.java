package com.example.tasktracking.service.impl;

import com.example.tasktracking.domain.mapper.TaskMapper;
import com.example.tasktracking.repository.TaskRepository;
import com.example.tasktracking.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }
}
