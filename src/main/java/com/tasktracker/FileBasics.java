package com.tasktracker;

import com.tasktracker.model.Task;
import com.tasktracker.repository.TaskRepository;

public class FileBasics {
    static void main() {
        var taskRepository = new TaskRepository();
        var tasks = taskRepository.load();
        System.out.println("Tareas cargadas: " + tasks);

        var task = new Task(1, "Buy groceries");

        tasks.add(task);
        taskRepository.save(tasks);
        System.out.println("Tarea guardada: " + task);
    }
}
