package org.makar.t1_tasks.service;

import lombok.extern.slf4j.Slf4j;
import org.makar.t1_tasks.dto.TaskStatusUpdateDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void taskUpdateNotification(TaskStatusUpdateDto taskDto){
        log.info(String.valueOf(taskDto.getId()));
    }
}
