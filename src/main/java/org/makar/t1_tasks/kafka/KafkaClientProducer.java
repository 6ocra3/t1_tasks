package org.makar.t1_tasks.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaClientProducer {

    private final KafkaTemplate template;

    public void send(Long id) {
        try {
            template.sendDefault(UUID.randomUUID().toString(), id).get();
            template.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void sendTo(String topic, Object o) {
        try {
            template.send(topic, o)
                    .whenComplete((result, exception) -> {
                        if (exception != null) {
                            log.error("Error sending message to topic {} {}", topic, exception);
                        } else {
                            log.info("Message sent successfully to topic {}: {}", topic, result);
                        }
                    });
            template.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}