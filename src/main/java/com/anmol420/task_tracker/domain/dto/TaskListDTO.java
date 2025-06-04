package com.anmol420.task_tracker.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDTO(
        UUID id,
        String title,
        String description,
        Integer count,
        double Progress,
        List<TaskDTO> tasks
) {
}
