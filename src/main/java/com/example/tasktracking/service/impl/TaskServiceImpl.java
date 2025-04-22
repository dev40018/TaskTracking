package com.example.tasktracking.service.impl;

import com.example.tasktracking.domain.Task;
import com.example.tasktracking.domain.TaskList;
import com.example.tasktracking.domain.TaskPriority;
import com.example.tasktracking.domain.TaskStatus;
import com.example.tasktracking.domain.mapper.TaskMapper;
import com.example.tasktracking.repository.TaskListRepository;
import com.example.tasktracking.repository.TaskRepository;
import com.example.tasktracking.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task Already Has an ID");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task Must have a Title");
        }
        TaskPriority  taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("invalid TaskList ID"));
        var now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                now,
                now,
                taskList
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTaskById(UUID taskListId, UUID taskId) {
       return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }
}
