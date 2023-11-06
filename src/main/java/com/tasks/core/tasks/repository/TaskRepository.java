package com.tasks.core.tasks.repository;

import com.tasks.core.tasks.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
