package com.taskmanage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskmanage.model.Task;
import com.taskmanage.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor injection (best practice)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get a task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Create/save a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Update an existing task
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setCompleted(taskDetails.isCompleted());
            return taskRepository.save(task);
        });
    }

    // Delete a task by ID
    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return true;
        }).orElse(false);
    }
}

