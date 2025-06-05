package com.anmol420.task_tracker.services.impl;

import com.anmol420.task_tracker.domain.entities.Task;
import com.anmol420.task_tracker.repositories.TaskRepository;
import com.anmol420.task_tracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

}
