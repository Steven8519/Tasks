package com.tasks.core.tasks.service;

import com.tasks.core.tasks.domain.Task;
import com.tasks.core.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    @Override
    public List<Task> getAllTasks() {
        List<Task> taskDetailsList = new ArrayList<>();
        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            Task taskDetails = new Task(
                    task.getTitle(),
                    task.getDescription(),
                    task.getCompleted(),
                    task.getCreateDate(),
                    task.getDateCompleted()
            );
            taskDetailsList.add(taskDetails);
        }

        return taskDetailsList;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskById(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElse(null);

        if (existingTask == null) {
            return null;
        }

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.getCompleted());
        existingTask.setDateCompleted(updatedTask.getDateCompleted());

        return taskRepository.save(existingTask);
    }
}
