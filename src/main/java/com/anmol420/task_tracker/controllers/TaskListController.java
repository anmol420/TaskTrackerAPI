package com.anmol420.task_tracker.controllers;

import com.anmol420.task_tracker.domain.dto.TaskListDTO;
import com.anmol420.task_tracker.domain.entities.TaskList;
import com.anmol420.task_tracker.mappers.TaskListMapper;
import com.anmol420.task_tracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/taskLists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping(path="/getTaskList")
    public List<TaskListDTO> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDTO)
                .toList();
    }

    @PostMapping(path="/createTask")
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO) {
        TaskList createdTaskList =  taskListService.createTaskList(
                taskListMapper.fromDTO(taskListDTO)
        );
        return taskListMapper.toDTO(createdTaskList);
    }
}
