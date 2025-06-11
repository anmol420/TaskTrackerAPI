# Spring Boot Task Tracker API

This project is a simple **Task Tracker RESTful API** built with **Spring Boot** and **Spring Data JPA**. It allows users to manage task lists and tasks with endpoints to create, read, update, and delete (CRUD) both entities.

---

## Features

- **Task Lists**: Create, retrieve, update, and delete task lists (CRUD)
-  **Tasks**: Add tasks to task lists with priority, status, and due date (CRUD)
-  Fully RESTful structure
- Postgres Database Connectivity

---
## Getting Started

### Prerequisites

- Java 21+
- Maven
- IDE (IntelliJ, VS Code, etc.)
- Docker
- PostgreSQL

### Setup Instructions

```bash
# Clone the repo
git clone https://github.com/anmol420/TaskTrackerAPI.git
cd TaskTrackerAPI

# Run Docker
docker compose up -d

# Build and run the app (example for Maven)
./mvnw spring-boot:run
```
---

## Project Structure

### Folders

- `TaskList Endpoints`: CRUD operations for task lists
- `Task Endpoints`: CRUD operations for tasks under a task list

### Example Endpoints

#### TaskList
- `POST /taskLists/createTask`
- `GET /taskLists/getTaskList`
- `GET /taskLists/getTaskList/{id}`
- `PUT /taskLists/updateTaskList/{id}`
- `DELETE /taskLists/deleteTaskList/{id}`

#### Task
- `POST /{taskListId}/tasks/createTask`
- `GET /{taskListId}/tasks/listTasks`
- `GET /{taskListId}/tasks/getTask/{taskId}`
- `PUT /{taskListId}/tasks/updateTask/{taskId}`
- `DELETE /{taskListId}/tasks/deleteTask/{taskId}`

---