package com.taskmanage.TaskRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanage.tasklist.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
