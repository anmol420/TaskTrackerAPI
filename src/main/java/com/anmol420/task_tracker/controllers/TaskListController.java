package com.anmol420.task_tracker.controllers;

import com.anmol420.task_tracker.domain.dto.TaskListDTO;
import com.anmol420.task_tracker.domain.entities.TaskList;
import com.anmol420.task_tracker.mappers.TaskListMapper;
import com.anmol420.task_tracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/taskLists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping(path = "/getTaskList")
    public List<TaskListDTO> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDTO)
                .toList();
    }

    @PostMapping(path = "/createTask")
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO) {
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDTO(taskListDTO)
        );
        return taskListMapper.toDTO(createdTaskList);
    }

    @GetMapping(path = "/getTaskList/{id}")
    public Optional<TaskListDTO> getTaskList(@PathVariable("id") UUID tasklistId) {
        return taskListService.getTaskList(tasklistId).map(taskListMapper::toDTO);
    }

    @PutMapping(path="/updateTaskList/{id}")
    public TaskListDTO updateTaskList(@PathVariable("id") UUID tasklistId, @RequestBody TaskListDTO taskListDTO) {
        TaskList updatedTaskList = taskListService.updateTaskList(tasklistId, taskListMapper.fromDTO(taskListDTO));
        return taskListMapper.toDTO(updatedTaskList);
    }

    @DeleteMapping(path="/deleteTaskList/{id}")
    public void deleteTaskList(@PathVariable("id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
    }
}
