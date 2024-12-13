package org.makar.t1_tasks.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.makar.t1_tasks.dto.TaskStatusUpdateDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    public void taskUpdateNotification(TaskStatusUpdateDto taskDto) {
        log.info("Task ID({}) new status {}", taskDto.getId(), taskDto.getStatus());
        sendEmail(taskDto);
    }

    private void sendEmail(TaskStatusUpdateDto taskDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("kmm6o3@yandex.ru");
            helper.setTo("mmkriazhev@edu.hse.ru");
            helper.setSubject("Task Update Notification");
            helper.setText("Task with ID " + taskDto.getId() + " has been updated.");

            mailSender.send(message);
            log.info("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
