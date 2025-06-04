package com.anmol420.task_tracker.mappers.impl;

import com.anmol420.task_tracker.domain.dto.TaskDTO;
import com.anmol420.task_tracker.domain.entities.Task;
import com.anmol420.task_tracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDTO(TaskDTO taskDTO) {
        return new Task(
                taskDTO.id(),
                null,
                null,
                taskDTO.taskPriority(),
                null,
                taskDTO.taskStatus(),
                taskDTO.description(),
                taskDTO.dueDate(),
                taskDTO.title()
        );
    }

    @Override
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
