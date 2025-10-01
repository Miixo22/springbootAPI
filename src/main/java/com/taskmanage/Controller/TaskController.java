package com.taskmanage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanage.model.Task;
import com.taskmanage.repository.TaskRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "CRUD operations for tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/ping")
    @Operation(summary = "Ping the server", description = "Returns 'pong' to verify server is alive")
    public String ping() {
        return "pong";
    }

    @PostMapping
    @Valid
    @Operation(summary = "Create a new task", description = "Adds a task to the database")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}/complete")
    @Operation(summary = "Mark task as completed", description = "Sets the completed flag to true for a given task ID")
    public Task markTaskAsCompleted(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Removes a task by ID")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        System.out.println("TaskController initialized");
    }
}
