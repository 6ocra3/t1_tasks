package org.makar.t1_tasks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.kafka.KafkaClientProducer;
import org.makar.t1_tasks.model.Task;
import org.makar.t1_tasks.repository.TaskRepository;
import org.makar.t1_tasks.utils.TaskMapper;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private KafkaClientProducer kafkaClientProducer;
    @Mock
    private NotificationService notificationService;

    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("test_title");
        task.setDescription("test_description");
        task.setStatus(1);
        task.setUserId(1L);
        taskService = new TaskService(taskRepository, kafkaClientProducer);
    }

    @Test
    @DisplayName("Тест создания задачи")
    void createTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        TaskDto createdTask = taskService.createTask(TaskMapper.toDto(task));
        Assertions.assertNotNull(createdTask);
        Assertions.assertEquals("test_title", createdTask.getTitle());
        Assertions.assertEquals("test_description", createdTask.getDescription());
        Assertions.assertEquals(1, createdTask.getStatus());
    }

    @Test
    @DisplayName("Тест получения задачи по ID")
    void getTaskById() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        when(taskRepository.getReferenceById(1L)).thenReturn(task);
        TaskDto foundTask = taskService.getTaskById(1L);
        Assertions.assertNotNull(foundTask);
        Assertions.assertEquals(1L, foundTask.getId());
        Assertions.assertEquals("test_title", foundTask.getTitle());
    }

    @Test
    @DisplayName("Тест обновления задачи по ID")
    void updateTaskById() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDto updateTask = new TaskDto();
        updateTask.setTitle("update_title");
        updateTask.setDescription("update_description");
        updateTask.setStatus(2);
        TaskDto task = taskService.updateTask(1L, updateTask);

        Assertions.assertNotNull(task);
        Assertions.assertEquals("test_title", task.getTitle());
        Assertions.assertEquals("test_description", task.getDescription());
        Assertions.assertEquals(1, task.getStatus());
    }

    @Test
    @DisplayName("Тест получения всех задач")
    void getAllTasks() {
        List<Task> tasks = Collections.singletonList(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        List<TaskDto> allTasks = taskService.getAllTasks();
        Assertions.assertNotNull(allTasks);
        Assertions.assertEquals(1, allTasks.size());
    }

    @Test
    @DisplayName("Тест удаления задачи по ID")
    void deleteTaskById() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        taskService.deleteTask(1L);
        verify(taskRepository).deleteById(1L);
    }
}
