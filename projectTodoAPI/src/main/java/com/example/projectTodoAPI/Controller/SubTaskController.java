package com.example.projectTodoAPI.Controller;

import com.example.projectTodoAPI.Entity.SubTask;
import com.example.projectTodoAPI.Service.SubTaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtasks")
public class SubTaskController {

    private final SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @PostMapping
    public ResponseEntity<?> createSubTask(HttpServletRequest request, @RequestParam Long taskId, @RequestBody SubTask subTask) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            SubTask createdSubTask = subTaskService.createSubTask(taskId, subTask, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSubTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating subtask: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getSubTasks(HttpServletRequest request, @RequestParam Long taskId) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            List<SubTask> subTasks = subTaskService.getSubTasks(taskId, userId);
            return ResponseEntity.ok(subTasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error fetching subtasks: " + e.getMessage());
        }
    }


    @PutMapping("/{subTaskId}")
    public ResponseEntity<?> updateSubTask(HttpServletRequest request, @PathVariable Long subTaskId, @RequestBody SubTask subTask) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            SubTask updatedSubTask = subTaskService.updateSubTask(subTaskId, subTask, userId);
            return ResponseEntity.ok(updatedSubTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating subtask: " + e.getMessage());
        }
    }


    @DeleteMapping("/{subTaskId}")
    public ResponseEntity<?> deleteSubTask(HttpServletRequest request, @PathVariable Long subTaskId) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // Extracted from JWT
            subTaskService.deleteSubTask(subTaskId, userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting subtask: " + e.getMessage());
        }
    }
}
