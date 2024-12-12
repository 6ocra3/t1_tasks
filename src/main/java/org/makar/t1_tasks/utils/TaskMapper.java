package org.makar.t1_tasks.utils;

import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public static Task toEntity(TaskDto taskDto){
        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .userId(taskDto.getUserId())
                .build();
    }

    public static TaskDto toDto(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .userId(task.getUserId())
                .build();
    }
}
