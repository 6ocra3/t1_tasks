package org.makar.t1_tasks.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.kafka.KafkaClientProducer;
import org.makar.t1_tasks.model.Task;
import org.makar.t1_tasks.repository.TaskRepository;
import org.makar.t1_tasks.utils.TaskMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final KafkaClientProducer kafkaClientProducer;
    @Value("t1_tasks_update_status")
    private String updateTopic;

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
        if(!Objects.equals(task.getStatus(), taskDetails.getStatus())){
            task.setStatus(taskDetails.getStatus());
            kafkaClientProducer.sendTo(updateTopic, TaskMapper.toStatusUpdateDto(task));
        }
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
