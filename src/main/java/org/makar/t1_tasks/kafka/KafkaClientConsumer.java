package org.makar.t1_tasks.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.makar.t1_tasks.dto.TaskDto;
import org.makar.t1_tasks.service.NotificationService;
import org.makar.t1_tasks.service.TaskService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaClientConsumer {

    private final NotificationService notificationService;

    @KafkaListener(id = "t1-tasks",
            topics = "t1_tasks_update_status",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload TaskDto message,
                         Acknowledgment ack,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.debug("Task update consumer: Обработка новых сообщений");
        try {
            notificationService.taskUpdateNotification(message);
        } finally {
            ack.acknowledge();
        }
        log.debug("Task update consumer: записи обработаны");
    }
}

