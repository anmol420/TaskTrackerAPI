package com.anmol420.task_tracker.mappers;

import com.anmol420.task_tracker.domain.dto.TaskListDTO;
import com.anmol420.task_tracker.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDTO(TaskListDTO taskListDTO);

    TaskListDTO toDTO(TaskList taskList);
}
