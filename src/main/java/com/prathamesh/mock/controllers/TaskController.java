package com.prathamesh.mock.controllers;

import com.prathamesh.mock.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Learn Spring Boot", true));
        tasks.add(new Task(2L, "Learn Jenkins", false));
        tasks.add(new Task(3L, "Deploy with Docker", false));
    }

    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId((long) (tasks.size() + 1));
        tasks.add(task);
        return task;
    }

    @GetMapping("/health")
    public String health() {
        return "Application is healthy";
    }
}