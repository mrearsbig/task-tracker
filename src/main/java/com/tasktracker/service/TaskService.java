package com.tasktracker.service;

import com.tasktracker.model.Task;
import com.tasktracker.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    public void addTask(String description) {
        // Lógica para agregar una nueva tarea
        List<Task> tasks = taskRepository.load();
        int newId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
        Task task = new Task(newId, description);
        tasks.add(task);
        taskRepository.save(tasks);
        System.out.printf("Task added successfully (ID: %d)%n", newId);
    }

    public void listTasks(String statusFilter) {
        // Lógica para listar tareas
        List<Task> tasks = taskRepository.load();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        if (statusFilter != null && !statusFilter.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> task.getStatus().equals(statusFilter))
                    .toList();

            if (tasks.isEmpty()) {
                System.out.printf("No tasks found with status '%s'.%n", statusFilter);
                return;
            }
        }

        tasks.forEach(task -> System.out.printf("%d: %s [%s]%n", task.getId(), task.getDescription(), task.getStatus()));
    }

    public void updateTask(int id, String newDescription) {
        // Lógica para actualizar la descripción de una tarea
        List<Task> tasks = taskRepository.load();
        var taskToUpdate = tasks.stream().filter(t -> t.getId() == id).findFirst();
        if (taskToUpdate.isPresent()) {
            Task task = taskToUpdate.get();
            task.setDescription(newDescription);
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(tasks);
            System.out.printf("Task (ID: %d) updated successfully.%n", id);
        } else {
            System.out.printf("Task with ID %d not found.%n", id);
        }
    }

    public void markTaskInProgress(int id) {
        // Lógica para marcar una tarea como en progreso
        List<Task> tasks = taskRepository.load();
        var taskToUpdate = tasks.stream().filter(t -> t.getId() == id).findFirst();
        if (taskToUpdate.isPresent()) {
            Task task = taskToUpdate.get();
            task.setStatus("in-progress");
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(tasks);
            System.out.printf("Task (ID: %d) marked as in-progress.%n", id);
        } else {
            System.out.printf("Task with ID %d not found.%n", id);
        }
    }

    public void markTaskDone(int id) {
        // Lógica para marcar una tarea como hecha
        List<Task> tasks = taskRepository.load();
        var taskToUpdate = tasks.stream().filter(t -> t.getId() == id).findFirst();
        if (taskToUpdate.isPresent()) {
            Task task = taskToUpdate.get();
            task.setStatus("done");
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(tasks);
            System.out.printf("Task (ID: %d) marked as done.%n", id);
        } else {
            System.out.printf("Task with ID %d not found.%n", id);
        }
    }

    public void deleteTask(int id) {
        // Lógica para eliminar una tarea
        List<Task> tasks = taskRepository.load();
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) {
            taskRepository.save(tasks);
            System.out.printf("Task (ID: %d) deleted successfully.%n", id);
        } else {
            System.out.printf("Task with ID %d not found.%n", id);
        }
    }
}
