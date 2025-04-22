package com.example.tasktracking.service;

import com.example.tasktracking.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface TaskService {
    List<Task> listTasks();
    Task createTask(UUID taskListId, Task task);
}
