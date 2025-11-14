package com.tasktracker;

import com.tasktracker.service.TaskService;

public class TaskTracker {
    static void main(String[] args) {
        var taskService = new TaskService();

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
                taskService.addTask(description);
            }

            case "list" -> {
                // Lógica para listar tareas
                if (args.length > 1) {
                    var statusFilter = args[1];

                    if (!statusFilter.equals("todo") && !statusFilter.equals("in-progress") && !statusFilter.equals("done")) {
                        System.out.println("Invalid status filter. Use 'todo', 'in-progress', or 'done'.");
                        return;
                    }

                    taskService.listTasks(statusFilter);
                } else {
                    taskService.listTasks(null);
                }
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

                taskService.updateTask(idToUpdate, newDescription);
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

                taskService.markTaskInProgress(idToMark);
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

                taskService.markTaskDone(idToMark);
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

                taskService.deleteTask(idToDelete);
            }

            default -> System.out.println("Unknown command: " + command);
        }
    }
}
