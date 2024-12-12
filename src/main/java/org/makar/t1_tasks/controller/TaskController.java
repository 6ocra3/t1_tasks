package org.makar.t1_tasks.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.makar.t1_tasks.aspect.annotation.LogError;
import org.makar.t1_tasks.aspect.annotation.LogResult;
import org.makar.t1_tasks.aspect.annotation.LogTimeExecution;
import org.makar.t1_tasks.aspect.annotation.ValidateId;
import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.kafka.KafkaClientProducer;
import org.makar.t1_tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final KafkaClientProducer kafkaClientProducer;
    @Value("t1_tasks_update_status")
    private String updateTopic;

    @GetMapping
    @LogResult
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @ValidateId
    public TaskDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    @LogError
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDetails) {
        TaskDto taskDto = taskService.updateTask(id, taskDetails);
        kafkaClientProducer.sendTo(updateTopic, taskDto);
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/{id}")
    @LogTimeExecution
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


}
