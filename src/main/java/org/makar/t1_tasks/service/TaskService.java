package org.makar.t1_tasks.service;

import org.makar.t1_tasks.model.Task;
import org.makar.t1_tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> GetTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task CreateTask(Task task) {
        return taskRepository.save(task);
    }

    public Task UpdateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        return taskRepository.save(task);
    }

    public void DeleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> GetAllTasks() {
        return taskRepository.findAll();
    }

}
