package org.makar.t1_tasks.mapper;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.model.Task;
import org.makar.t1_tasks.utils.TaskMapper;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TaskMapperTest {


    @Test
    @DisplayName("Тест маппинга из task dto в task entity")
    void testConvertToTaskEntity() {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("test_title");
        taskDto.setDescription("test_description");
        taskDto.setUserId(1L);
        taskDto.setStatus(1);
        Task task = TaskMapper.toEntity(taskDto);
        Assertions.assertNotNull(task);
        Assertions.assertEquals(taskDto.getTitle(), task.getTitle());
        Assertions.assertEquals(taskDto.getDescription(), task.getDescription());
        Assertions.assertEquals(taskDto.getUserId(), task.getUserId());
        Assertions.assertEquals(taskDto.getStatus(), task.getStatus());
    }

    @Test
    @DisplayName("Тест маппинга из task entity в task dto")
    void testConvertTaskToDto() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("test_title");
        task.setDescription("test_description");

        TaskDto taskDto = TaskMapper.toDto(task);

        Assertions.assertNotNull(taskDto);
        Assertions.assertEquals(task.getId(), taskDto.getId());
        Assertions.assertEquals(task.getTitle(), taskDto.getTitle());;
    }
}