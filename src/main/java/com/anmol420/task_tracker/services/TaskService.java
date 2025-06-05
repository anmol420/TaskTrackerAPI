package com.anmol420.task_tracker.services;

import com.anmol420.task_tracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
}
