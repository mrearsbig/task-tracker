package com.tasktracker;

import com.tasktracker.model.Task;
import com.tasktracker.repository.TaskRepository;

import java.time.LocalDateTime;

public class TaskTracker {
    static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("    add <description> - Add a new task");
            System.out.println("    list [status] - List tasks, optionally filtered by status (todo, in-progress, done)");
            System.out.println("    update <id> <new description> - Update a task's description");
            System.out.println("    mark-in-progress <id> - Mark a task as in progress");
            System.out.println("    mark-done <id> - Mark a task as done");
            System.out.println("    delete <id> - Delete a task");
            return;
        }

        var command = args[0];

        switch (command) {
            case "add" -> {
                // Lógica para agregar una nueva tarea
                if (args.length < 2) {
                    System.out.println("Please provide a description for the new task.");
                    System.out.println("Usage: add <description>");
                    return;
                }

                var description = args[1];
                var taskRepository = new TaskRepository();
                var tasks = taskRepository.load();

                var newId = tasks.stream().mapToInt(t -> t.getId()).max().orElse(0) + 1;
                System.out.println("Nuevo ID de tarea: " + newId);

                var task = new Task(newId, description);

                tasks.add(task);
                taskRepository.save(tasks);

                System.out.println("Task added: " + task);
            }

            case "list" -> {
                // Lógica para listar tareas
                var taskRepository = new TaskRepository();
                var tasks = taskRepository.load();

                if (tasks.isEmpty()) {
                    System.out.println("No tasks found.");
                }

                if (args.length > 1) {
                    var statusFilter = args[1];

                    if (!statusFilter.equals("todo") && !statusFilter.equals("in-progress") && !statusFilter.equals("done")) {
                        System.out.println("Invalid status filter. Use 'todo', 'in-progress', or 'done'.");
                        return;
                    }

                    var filteredTasks = tasks.stream()
                            .filter(task -> task.getStatus().equals(statusFilter))
                            .toList();

                    tasks = filteredTasks;
                }

                tasks.forEach(task -> System.out.println(task.getId() + ": " + task.getDescription() + " [" + task.getStatus() + "]"));
            }

            case "update" -> {
                // Lógica para actualizar una tarea
                if (args.length < 3) {
                    System.out.println("Please provide the ID and new description for the task.");
                    System.out.println("Usage: update <id> <new description>");
                    return;
                }

                int idToUpdate;

                try {
                    idToUpdate = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("The task ID must be a valid integer.");
                    return;
                }

                var newDescription = args[2];
                var taskRepository = new TaskRepository();
                var tasks = taskRepository.load();

                var taskToUpdate = tasks.stream().filter(task -> task.getId() == idToUpdate).findFirst();

                if (taskToUpdate.isPresent()) {
                    var task = taskToUpdate.get();
                    task.setDescription(newDescription);
                    task.setUpdatedAt(LocalDateTime.now());
                    taskRepository.save(tasks);
                    System.out.println("Task updated: " + task);
                } else {
                    System.out.println("Task with ID " + idToUpdate + " not found.");
                }
            }

            case "mark-in-progress" -> {
                // Lógica para marcar una tarea como en progreso
                if (args.length < 2) {
                    System.out.println("Please provide the ID of the task to mark as in progress.");
                    System.out.println("Usage: mark-in-progress <id>");
                    return;
                }

                int idToMark;

                try {
                    idToMark = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("The task ID must be a valid integer.");
                    return;
                }

                var taskRepository = new TaskRepository();
                var tasks = taskRepository.load();

                var taskToMark = tasks.stream().filter(task -> task.getId() == idToMark).findFirst();

                if (taskToMark.isPresent()) {
                    var task = taskToMark.get();
                    task.setStatus("in-progress");
                    task.setUpdatedAt(LocalDateTime.now());
                    taskRepository.save(tasks);
                    System.out.println("Task marked as in progress: " + task);
                } else {
                    System.out.println("Task with ID " + idToMark + " not found.");
                }
            }

            case "mark-done" -> {
                // Lógica para marcar una tarea como hecha
                if (args.length < 2) {
                    System.out.println("Please provide the ID of the task to mark as done.");
                    System.out.println("Usage: mark-done <id>");
                    return;
                }

                int idToMark;

                try {
                    idToMark = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("The task ID must be a valid integer.");
                    return;
                }

                var taskRepository = new TaskRepository();
                var tasks = taskRepository.load();

                var taskToMark = tasks.stream().filter(task -> task.getId() == idToMark).findFirst();

                if (taskToMark.isPresent()) {
                    var task = taskToMark.get();
                    task.setStatus("done");
                    task.setUpdatedAt(LocalDateTime.now());
                    taskRepository.save(tasks);
                    System.out.println("Task marked as done: " + task);
                } else {
                    System.out.println("Task with ID " + idToMark + " not found.");
                }
            }

            case "delete" -> {
                // Lógica para eliminar una tarea
                if (args.length < 2) {
                    System.out.println("Please provide the ID of the task to delete.");
                    System.out.println("Usage: delete <id>");
                    return;
                }

                int idToDelete;

                try {
                    idToDelete = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("The task ID must be a valid integer.");
                    return;
                }

                var taskRepository = new TaskRepository();
                var tasks = taskRepository.load();

                var removed = tasks.removeIf(task -> task.getId() == idToDelete);

                if (removed) {
                    taskRepository.save(tasks);
                    System.out.println("Task with ID " + idToDelete + " deleted.");
                } else {
                    System.out.println("Task with ID " + idToDelete + " not found.");
                }
            }

            default -> System.out.println("Comando no reconocido: " + command);
        }
    }
}
