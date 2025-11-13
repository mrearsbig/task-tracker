package com.tasktracker.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tasktracker.model.Task;
import com.tasktracker.util.LocalDateTimeAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class TaskRepository {
    private final Path file = Path.of("tasks.json");
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public List<Task> load() {
        try {
            // Si el archivo no existe, crearlo con un array vacío
            if (Files.notExists(file)) {
                Files.writeString(file, "[]");
            }

            // Leer el contenido del archivo
            var json = Files.readString(file);

            // Definir el tipo de la lista de tareas
            var type = new TypeToken<List<Task>>() {
            }.getType();

            // Convertir el JSON a una lista de tareas
            return gson.fromJson(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //findByStatus

    public void save(List<Task> tasks) {
        // Convertir la lista de tareas a JSON
        var json = gson.toJson(tasks);

        try {
            // Escribir el JSON en el archivo
            Files.writeString(file, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
