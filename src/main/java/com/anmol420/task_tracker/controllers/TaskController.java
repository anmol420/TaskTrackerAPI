package com.anmol420.task_tracker.controllers;

import com.anmol420.task_tracker.domain.dto.TaskDTO;
import com.anmol420.task_tracker.domain.entities.Task;
import com.anmol420.task_tracker.mappers.TaskMapper;
import com.anmol420.task_tracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PostMapping(path="/createTask")
    public TaskDTO createTask(@PathVariable("taskListId") UUID taskListId, @RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDTO(taskDTO));
        return taskMapper.toDTO(createdTask);
    }

    @GetMapping(path="/getTask/{id}")
    public Optional<TaskDTO> getTask(@PathVariable("taskListId") UUID taskListId, @PathVariable("id") UUID id) {
        return taskService.getTask(taskListId, id)
                .map(taskMapper::toDTO);
    }

    @PutMapping(path="/updateTask/{id}")
    public TaskDTO updateTask(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("id") UUID id,
            @RequestBody TaskDTO taskDTO
    ) {
        Task updatedTask = taskService.updateTask(taskListId, id, taskMapper.fromDTO(taskDTO));
        return taskMapper.toDTO(updatedTask);
    }

    @DeleteMapping(path="/deleteTask/{id}")
    public void deleteTask(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("id") UUID id
    ) {
        taskService.deleteTask(taskListId, id);
    }
}
