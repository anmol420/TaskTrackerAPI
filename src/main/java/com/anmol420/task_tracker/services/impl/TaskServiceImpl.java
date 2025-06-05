package com.anmol420.task_tracker.services.impl;

import com.anmol420.task_tracker.domain.entities.Task;
import com.anmol420.task_tracker.domain.entities.TaskList;
import com.anmol420.task_tracker.domain.entities.TaskPriority;
import com.anmol420.task_tracker.domain.entities.TaskStatus;
import com.anmol420.task_tracker.repositories.TaskListRepository;
import com.anmol420.task_tracker.repositories.TaskRepository;
import com.anmol420.task_tracker.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID tasklistId, Task task) {
        if (null != task.getId()) {
            throw new IllegalArgumentException("Task Already Has An ID!");
        }
        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task Title is Required!");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(tasklistId)
                .orElseThrow(() -> new IllegalArgumentException("TaskList Doesn't Exists!"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                now,
                now,
                taskPriority,
                taskList,
                taskStatus,
                task.getDescription(),
                task.getDueDate(),
                task.getTitle()
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (null == task.getId()) {
            throw new IllegalArgumentException("Task ID is Required!");
        }
        if (!Objects.equals(task.getId(), taskId)) {
            throw new IllegalArgumentException("Task ID do not match!");
        }
        if (null == task.getPriority()) {
            throw new IllegalArgumentException("Task Priority Required!");
        }
        if (null == task.getStatus()) {
            throw new IllegalArgumentException("Task Status Required!");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task Doesn't Exists!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }

}
