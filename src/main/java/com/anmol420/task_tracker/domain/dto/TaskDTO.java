package com.anmol420.task_tracker.domain.dto;

import com.anmol420.task_tracker.domain.entities.TaskPriority;
import com.anmol420.task_tracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority taskPriority,
        TaskStatus taskStatus
) {
}
