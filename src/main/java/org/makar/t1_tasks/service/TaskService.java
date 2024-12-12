package org.makar.t1_tasks.service;

import lombok.AllArgsConstructor;
import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.model.Task;
import org.makar.t1_tasks.repository.TaskRepository;
import org.makar.t1_tasks.utils.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskDto getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(TaskMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskRepository.save(TaskMapper.toEntity(taskDto));
        return TaskMapper.toDto(task);
    }

    public TaskDto updateTask(Long id, TaskDto taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        return TaskMapper.toDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

}
