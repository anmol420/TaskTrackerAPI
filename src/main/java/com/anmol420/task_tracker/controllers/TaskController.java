package com.anmol420.task_tracker.controllers;

import com.anmol420.task_tracker.domain.dto.TaskDTO;
import com.anmol420.task_tracker.mappers.TaskMapper;
import com.anmol420.task_tracker.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/{taskListId}/tasks")
public class TaskController {

    final private TaskService taskService;
    final private TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping(path="/listTasks")
    public List<TaskDTO> listTasks(@PathVariable("taskListId") UUID taskListId) {
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDTO)
                .toList();
    }
}
