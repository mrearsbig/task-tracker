# Task Tracker CLI

Task Tracker es una aplicación de línea de comandos (CLI) para rastrear y gestionar tus tareas. Este proyecto te permite
practicar habilidades de programación, incluyendo el trabajo con el sistema de archivos, el manejo de entradas de
usuario y la construcción de aplicaciones CLI.

## Descripción

Esta aplicación te permite realizar un seguimiento de lo que necesitas hacer, lo que has hecho y en qué estás trabajando
actualmente. Todas las tareas se almacenan en un archivo JSON en el directorio actual.

## Características

- ✅ Agregar, actualizar y eliminar tareas
- ✅ Marcar tareas como "en progreso" o "completadas"
- ✅ Listar todas las tareas
- ✅ Listar tareas por estado (completadas, pendientes, en progreso)
- ✅ Almacenamiento persistente en archivo JSON
- ✅ Manejo de errores y casos extremos

## Requisitos

- Java 21
- Gradle (incluido en el proyecto con Gradle Wrapper)

## Instalación

1. Clona este repositorio:

```bash
git clone <repository-url>
cd task-tracker
```

2. Compila el proyecto:

```bash
./gradlew build
```

## Uso

### Comandos Disponibles

#### Agregar una nueva tarea

```bash
./gradlew run --args='add "Comprar víveres"'
# Salida: Task added successfully (ID: 1)
```

#### Actualizar una tarea

```bash
./gradlew run --args='update 1 "Comprar víveres y cocinar la cena"'
# Salida: Task updated successfully (ID: 1)
```

#### Eliminar una tarea

```bash
./gradlew run --args='delete 1'
# Salida: Task deleted successfully (ID: 1)
```

#### Marcar una tarea como en progreso

```bash
./gradlew run --args='mark-in-progress 1'
# Salida: Task marked as in progress (ID: 1)
```

#### Marcar una tarea como completada

```bash
./gradlew run --args='mark-done 1'
# Salida: Task marked as done (ID: 1)
```

#### Listar todas las tareas

```bash
./gradlew run --args='list'
```

#### Listar tareas completadas

```bash
./gradlew run --args='list done'
```

#### Listar tareas pendientes

```bash
./gradlew run --args='list todo'
```

#### Listar tareas en progreso

```bash
./gradlew run --args='list in-progress'
```

## Propiedades de las Tareas

Cada tarea tiene las siguientes propiedades:

- **id**: Identificador único de la tarea
- **description**: Descripción breve de la tarea
- **status**: Estado de la tarea (`todo`, `in-progress`, `done`)
- **createdAt**: Fecha y hora de creación de la tarea
- **updatedAt**: Fecha y hora de la última actualización

## Estructura del Proyecto

```
task-tracker/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── tasktracker/
│                   ├── TaskTracker.java          # Punto de entrada de la aplicación
│                   ├── model/
│                   │   └── Task.java             # Modelo de datos de tarea
│                   ├── repository/
│                   │   └── TaskRepository.java   # Gestión de persistencia JSON
│                   ├── service/
│                   │   └── TaskService.java      # Lógica de negocio
│                   └── util/
│                       └── LocalDateTimeAdapter.java # Adaptador para serialización de fechas
├── tasks.json                                     # Archivo de almacenamiento (generado automáticamente)
├── build.gradle                                   # Configuración de Gradle
└── README.md
```

## Almacenamiento de Datos

Las tareas se almacenan en un archivo `tasks.json` en el directorio actual del proyecto. Este archivo se crea
automáticamente si no existe. Ejemplo de estructura:

```json
[
  {
    "id": 1,
    "description": "Comprar víveres",
    "status": "todo",
    "createdAt": "2025-11-13T10:30:00",
    "updatedAt": "2025-11-13T10:30:00"
  },
  {
    "id": 2,
    "description": "Completar proyecto",
    "status": "in-progress",
    "createdAt": "2025-11-13T11:00:00",
    "updatedAt": "2025-11-13T12:00:00"
  }
]
```

## Implementación Técnica

- **Lenguaje**: Java
- **Gestión de dependencias**: Gradle
- **Serialización JSON**: Gson
- **Sistema de archivos**: Java NIO (java.nio.file)
- **Manejo de fechas**: java.time.LocalDateTime

## Características Técnicas

- ✅ No utiliza frameworks externos (solo Gson para JSON)
- ✅ Utiliza argumentos posicionales en línea de comandos
- ✅ Manejo robusto de errores
- ✅ Validación de entradas de usuario
- ✅ Gestión automática de IDs únicos
- ✅ Actualización automática de timestamps

## Desarrollo

### Compilar el proyecto

```bash
./gradlew build
```

### Ejecutar tests

```bash
./gradlew test
```

### Limpiar archivos compilados

```bash
./gradlew clean
```

## Manejo de Errores

La aplicación maneja los siguientes casos:

- IDs de tarea no válidos
- Tareas no encontradas
- Archivos JSON corruptos
- Comandos no reconocidos
- Argumentos faltantes o incorrectos

## Contribución

Este proyecto fue desarrollado como parte del desafío de programación
de [roadmap.sh](https://roadmap.sh/projects/task-tracker).

## Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## Autor

Desarrollado como proyecto de práctica para mejorar habilidades en:

- Programación orientada a objetos
- Manejo del sistema de archivos
- Desarrollo de aplicaciones CLI
- Gestión de datos con JSON
- Arquitectura de software (Repository Pattern, Service Layer)

