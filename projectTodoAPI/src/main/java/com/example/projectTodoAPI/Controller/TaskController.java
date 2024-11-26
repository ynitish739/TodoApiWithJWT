package com.example.projectTodoAPI.Controller;

import com.example.projectTodoAPI.Entity.Task;
import com.example.projectTodoAPI.Service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<?> createTask(HttpServletRequest request, @RequestBody Task task) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            System.out.println("UserId from request: " + userId);
            Task createdTask = taskService.createTask(userId, task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating task: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getUserTasks(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            List<Task> tasks = taskService.getUserTasks(userId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error fetching tasks: " + e.getMessage());
        }
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(
            HttpServletRequest request,
            @PathVariable Long taskId,
            @RequestBody Map<String, Object> updates) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            Task updatedTask = taskService.updateTask(taskId, userId, updates);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating task: " + e.getMessage());
        }
    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(HttpServletRequest request, @PathVariable Long taskId) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            taskService.deleteTask(taskId, userId);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting task: " + e.getMessage());
        }
    }
}