# TodoApiWithJWT

https://github.com/user-attachments/assets/3eda626a-10a1-4db1-8477-08b984e2a659

Here's a simple README file for your project:

---

# ProjectTodoAPI

## Overview
The **ProjectTodoAPI** is a simple Spring Boot REST API that manages tasks and subtasks. It uses an **in-memory H2 database** for storing data, which means you don't need to set up an external database to start working with the project. Just download the files, run the application, and you're ready to go.

## Requirements
- **Java 21**
- **Maven**

## Setup and Running the Application

### 1. Clone the Project
Clone the repository to your local machine:
```bash
git clone <repository-url>
```

### 2. Build the Project
Navigate to the project directory and build the project using Maven or Gradle.

- **Using Maven**:
  ```bash
  mvn clean install
  ```

### 3. Run the Application
Run the Spring Boot application:

```bash
mvn spring-boot:run
```


### 4. Access the API
Once the application is running, you can access the following API endpoints using Postman or any HTTP client:

- **POST** `/tasks` - Create a new task.
- **GET** `/tasks` - Retrieve all tasks.
- **GET** `/tasks/{id}` - Get a specific task by ID.
- **PUT** `/tasks/{id}` - Update a task by ID.
- **DELETE** `/tasks/{id}` - Delete a task by ID.
- **POST** `/tasks/{taskId}/subtasks` - Create a new subtask for a task.
- **GET** `/tasks/{taskId}/subtasks` - Retrieve all subtasks for a task.
- **GET** `/subtasks/{id}` - Get a specific subtask by ID.
- **PUT** `/subtasks/{id}` - Update a subtask by ID.
- **DELETE** `/subtasks/{id}` - Delete a subtask by ID.

### 5. In-Memory H2 Database
The application uses an **H2 in-memory database**, so there's no need for any external database configuration. Once the application is started, H2 will automatically create the tables and store the data in memory. 

You can view the H2 database console by accessing the following URL in your browser:
```
http://localhost:8080/h2-console
```

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

### 6. Stopping the Application
To stop the application, press `Ctrl + C` in your terminal.

## Conclusion
That's it! With these simple steps, you can run and interact with the **ProjectTodoAPI** without any additional setup. Enjoy using the API and feel free to extend it!

--- 

This README provides a concise guide to getting started with the project.
