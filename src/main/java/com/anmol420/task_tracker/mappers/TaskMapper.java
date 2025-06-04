package com.anmol420.task_tracker.mappers;

import com.anmol420.task_tracker.domain.dto.TaskDTO;
import com.anmol420.task_tracker.domain.entities.Task;

public interface TaskMapper {

    Task fromDTO(TaskDTO taskDTO);

    TaskDTO toDTO(Task task);
}
