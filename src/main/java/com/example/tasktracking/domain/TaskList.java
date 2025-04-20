package com.example.tasktracking.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_tasks_list")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;


    @Column(name = "created_at", nullable = false)
    //@CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    //@UpdateTimestamp
    private LocalDateTime updatedAt;

    //bidirectional cuz we can access task when we load tasks, and we can access tasklist in taskLists(Task DTO)
    @OneToMany(
            mappedBy = "taskList", // name of instance inside Task
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Task> tasks;

}
