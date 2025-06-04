package com.anmol420.task_tracker.mappers.impl;

import com.anmol420.task_tracker.domain.dto.TaskListDTO;
import com.anmol420.task_tracker.domain.entities.Task;
import com.anmol420.task_tracker.domain.entities.TaskList;
import com.anmol420.task_tracker.domain.entities.TaskStatus;
import com.anmol420.task_tracker.mappers.TaskListMapper;
import com.anmol420.task_tracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDTO(TaskListDTO taskListDTO) {
        return new TaskList(
                taskListDTO.id(),
                taskListDTO.title(),
                taskListDTO.description(),
                Optional.ofNullable(taskListDTO.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDTO)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDTO toDTO(TaskList taskList) {
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(task -> task.stream()
                                .map(taskMapper::toDTO)
                                .toList()
                        ).orElse(null)
        );
    }

    private double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks) {
            return 0;
        }
        long closedTaskCount = tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus()
        ).count();

        return (double) closedTaskCount / tasks.size();
    }
}
