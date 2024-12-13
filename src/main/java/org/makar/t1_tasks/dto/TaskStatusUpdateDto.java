package org.makar.t1_tasks.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusUpdateDto {
    private Long id;
    private Integer status;
}