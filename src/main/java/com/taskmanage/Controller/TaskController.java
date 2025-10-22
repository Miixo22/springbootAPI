package com.taskmanage.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanage.model.Task;
import com.taskmanage.repository.TaskRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "CRUD operations for tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TaskController.class);

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
    public List<Task> getAllTasks() {
        logger.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    @GetMapping("/ping")
    @Operation(summary = "Ping the server", description = "Returns 'pong' to verify server is alive")
    public String ping() {
        return "pong";
    }

    // @PostMapping
    // @Valid
    // @Operation(summary = "Create a new task", description = "Adds a task to the database")
    // public Task createTask(@RequestBody Task task) {
    //     return taskRepository.save(task);
    // }

    @PostMapping("/createtask")
    public ResponseEntity<Task> createTask(@RequestParam String title, @RequestParam String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{id}/complete")
    @Operation(summary = "Mark task as completed", description = "Sets the completed flag to true for a given task ID")
    public Task markTaskAsCompleted(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    @GetMapping("/filter")
    public List<Task> filterByStatus(@RequestParam boolean completed) {
        logger.info("Filtering tasks by completed status.");
        return taskRepository.findByCompleted(completed);
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
