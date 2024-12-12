package org.makar.t1_tasks.service;

import lombok.extern.slf4j.Slf4j;
import org.makar.t1_tasks.dto.TaskDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void taskUpdateNotification(TaskDto taskDto){
        log.info(String.valueOf(taskDto.getId()));
    }
}