package com.taskmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanage.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
