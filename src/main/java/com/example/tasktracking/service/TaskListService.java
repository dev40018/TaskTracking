package com.example.tasktracking.service;

import com.example.tasktracking.domain.Task;
import com.example.tasktracking.domain.TaskList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TaskListService {
    List<TaskList> listTaskList();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID taskListId);
}
