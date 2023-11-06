package com.tasks.core.tasks.service;

import com.tasks.core.tasks.domain.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasks();

    Task updateTaskById(Long id, Task updatedTask);
}
