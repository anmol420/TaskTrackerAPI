package com.anmol420.task_tracker.services.impl;

import com.anmol420.task_tracker.domain.entities.TaskList;
import com.anmol420.task_tracker.repositories.TaskListRepository;
import com.anmol420.task_tracker.services.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("TaskList Already has an ID!");
        }
        if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList Title must not be Blank!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {
        if (null == taskList.getId()) {
            throw new IllegalArgumentException("TaskList must have an ID!");
        }
        if (!Objects.equals(taskList.getId(), id)) {
            throw new IllegalArgumentException("IDs don't match!");
        }
        TaskList existingTaskList = taskListRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("TaskList Not Found!")
        );
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID id) {
        if (null == id) {
            throw new IllegalArgumentException("ID must be provided!");
        }
        taskListRepository.deleteById(id);
    }
}
