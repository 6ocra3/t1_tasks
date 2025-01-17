package org.makar.t1_tasks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.makar.t1_tasks.AbstractContainerBaseTest;
import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.model.Task;
import org.makar.t1_tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yaml")
public class TaskServiceIntegrationTest extends AbstractContainerBaseTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
        saveTestTasks();
    }

    @Test
    @DisplayName("Проверка удаления таски из репозитория")
    public void deleteByIdTest() {
        taskService.deleteTask(1L);
        List<TaskDto> tasks = taskService.getAllTasks();
        Assertions.assertNotNull(tasks);
        Assertions.assertEquals(1, tasks.size());
    }

    @Test
    @DisplayName("Проверка получения таски по id")
    public void getTaskById() {
        TaskDto task = taskService.getTaskById(2L);
        Assertions.assertNotNull(task);
        Assertions.assertEquals(2, task.getStatus());
        Assertions.assertEquals("test_title2", task.getTitle());
        Assertions.assertEquals("test_description2", task.getDescription());
    }


    private void saveTestTasks() {
        Task task1 = new Task();
        task1.setTitle("test_title1");
        task1.setDescription("test_description1");
        task1.setStatus(1);
        task1.setUserId(1L);

        Task task2 = new Task();
        task2.setTitle("test_title2");
        task2.setDescription("test_description2");
        task2.setStatus(2);
        task2.setUserId(10L);

        taskRepository.save(task1);
        taskRepository.save(task2);
    }


}
