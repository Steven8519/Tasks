package com.tasks.core.tasks.service;

import com.tasks.core.tasks.domain.Task;
import com.tasks.core.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    void testGetAllTaskDetails() {
        Task task1 = new Task("Task 1", "Description 1", true, new Date(), new Date());
        Task task2 = new Task("Task 2", "Description 2", false, new Date(), null);

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> taskDetailsList = taskService.getAllTasks();

        assertEquals(2, taskDetailsList.size());
        assertEquals("Task 1", taskDetailsList.get(0).getTitle());
        assertEquals("Description 1", taskDetailsList.get(0).getDescription());
        assertEquals(true, taskDetailsList.get(0).getCompleted());


        assertEquals("Task 2", taskDetailsList.get(1).getTitle());
        assertEquals("Description 2", taskDetailsList.get(1).getDescription());
        assertEquals(false, taskDetailsList.get(1).getCompleted());
    }

    @Test
    void testCreateTask() {
        Task sampleTask = new Task();
        sampleTask.setId(1L);
        sampleTask.setTitle("Sample Task");
        sampleTask.setDescription("Sample Description");

        when(taskRepository.save(sampleTask)).thenReturn(sampleTask);

        Task createdTask = taskService.createTask(sampleTask);

        assertEquals(1L, createdTask.getId());
        assertEquals("Sample Task", createdTask.getTitle());
        assertEquals("Sample Description", createdTask.getDescription());
    }


    @Test
    void testGetTaskById() {
        Task sampleTask = new Task();
        sampleTask.setId(1L);
        sampleTask.setTitle("Sample Task");
        sampleTask.setDescription("Sample Description");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask));

        Task retrievedTask = taskService.getTaskById(1L);

        assertEquals(1L, retrievedTask.getId());
        assertEquals("Sample Task", retrievedTask.getTitle());
        assertEquals("Sample Description", retrievedTask.getDescription());
    }


    @Test
    void testUpdateTaskById() {
        Task sampleTask = new Task();
        sampleTask.setId(1L);
        sampleTask.setTitle("Sample Task");
        sampleTask.setDescription("Sample Description");

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask));

        when(taskRepository.save(sampleTask)).thenReturn(updatedTask);

        Task updatedTaskResult = taskService.updateTaskById(1L, updatedTask);

        assertEquals("Updated Task", updatedTaskResult.getTitle());
        assertEquals("Updated Description", updatedTaskResult.getDescription());
    }
}

