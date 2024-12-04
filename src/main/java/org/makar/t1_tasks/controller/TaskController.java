package org.makar.t1_tasks.controller;

import org.makar.t1_tasks.aspect.annotation.LogError;
import org.makar.t1_tasks.aspect.annotation.LogResult;
import org.makar.t1_tasks.aspect.annotation.LogTimeExecution;
import org.makar.t1_tasks.aspect.annotation.ValidateId;
import org.makar.t1_tasks.model.Task;
import org.makar.t1_tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @LogResult
    public List<Task> GetAllTasks() {
        return taskService.GetAllTasks();
    }

    @GetMapping("/{id}")
    @ValidateId
    public ResponseEntity<Task> GetTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.GetTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task GreateTask(@RequestBody Task task) {
        return taskService.CreateTask(task);
    }

    @PutMapping("/{id}")
    @LogError
    public ResponseEntity<Task> UpdateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return ResponseEntity.ok(taskService.UpdateTask(id, taskDetails));
    }

    @DeleteMapping("/{id}")
    @LogTimeExecution
    public ResponseEntity<Void> DeleteTask(@PathVariable Long id) {
        taskService.DeleteTask(id);
        return ResponseEntity.noContent().build();
    }


}
