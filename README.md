# TodoApiWithJWT

https://github.com/user-attachments/assets/3eda626a-10a1-4db1-8477-08b984e2a659


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

### 4. User Authentication Flow

To interact with the APIs that require authentication, follow this flow:

1. **Create a User**:  
   First, you need to create a user by sending a `POST` request to the `/users` endpoint.
   
   - **Endpoint**: `POST /users`
   - **Request Body**: 
     ```json
     {
       "username": "exampleUsername",
       "password": "examplePassword"
     }
     ```

   This will return the `userId` for the created user.

2. **Generate Token**:  
   After creating the user, use the `userId` to generate an authentication token by sending a `POST` request to the `/auth/token` endpoint.
   
   - **Endpoint**: `POST /auth/token`
   - **Request Body**:
     ```json
     {
       "userId": "userId_from_previous_step",
       "password": "examplePassword"
     }
     ```
   
   This will return a **JWT token** which you will use in subsequent requests for authorization.

3. **Use Token for API Requests**:  
   Now that you have the JWT token, you can use it to access any of the other API endpoints. For example, to create a new task or get tasks, you will need to pass the token in the `Authorization` header of your request:
   
   - **Authorization Header**: `Bearer <your-jwt-token>`
   
   Example of how to use the token in a request header:
   ```bash
   curl -X GET "http://localhost:8080/tasks" -H "Authorization: Bearer <your-jwt-token>"
   ```


### 5. Access the API
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

### 6. In-Memory H2 Database
The application uses an **H2 in-memory database**, so there's no need for any external database configuration. Once the application is started, H2 will automatically create the tables and store the data in memory. 

You can view the H2 database console by accessing the following URL in your browser:
```
http://localhost:8080/h2-console
```

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

### 7. Stopping the Application
To stop the application, press `Ctrl + C` in your terminal.

## Conclusion
That's it! With these simple steps, you can run and interact with the **ProjectTodoAPI** without any additional setup. Enjoy using the API and feel free to extend it!

--- 

