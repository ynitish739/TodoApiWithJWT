package com.example.projectTodoAPI.Service;

import com.example.projectTodoAPI.Entity.SubTask;
import com.example.projectTodoAPI.Entity.Task;
import com.example.projectTodoAPI.Entity.app_User;
import com.example.projectTodoAPI.Repository.SubTaskRepository;
import com.example.projectTodoAPI.Repository.TaskRepository;
import com.example.projectTodoAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final SubTaskRepository subTaskRepository;
    @Autowired
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserService userService, SubTaskRepository subTaskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.subTaskRepository = subTaskRepository;
        this.userRepository = userRepository;
    }


    public Task createTask(Long userId, Task task) {

        app_User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error fetching user: The given id must not be null"));


        task.setUser(user);


        return taskRepository.save(task);
    }


    public List<Task> getUserTasks(Long userId) {
        try {
            // Validate user existence
            userService.getUserById(userId); // Ensures the user exists
            return taskRepository.findByUserIdAndIsDeletedFalse(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching tasks for user ID " + userId + ": " + e.getMessage());
        }
    }
    public Task getTaskById(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("User is not authorized to access this task");
        }
        return task;
    }
    public Task updateTask(Long taskId, Long userId, Map<String, Object> updates) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));

        if (!existingTask.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this task.");
        }


        if (updates.containsKey("title")) {
            existingTask.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("dueDate")) {
            existingTask.setDueDate(LocalDate.parse((String) updates.get("dueDate")));
        }
        if (updates.containsKey("priority")) {
            existingTask.setPriority((Integer) updates.get("priority"));
        }
        if (updates.containsKey("status")) {
            int newStatus = (Integer) updates.get("status");
            existingTask.setStatus(newStatus);


            if (newStatus == 1) {
                List<SubTask> subTasks = subTaskRepository.findByTaskIdAndDeletedFalse(taskId);
                for (SubTask subTask : subTasks) {
                    subTask.setStatus(1); // Mark subtask as done
                    subTaskRepository.save(subTask);
                }
            }
        }

        return taskRepository.save(existingTask);
    }


    public void deleteTask(Long taskId, Long userId) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));

        if (!existingTask.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this task.");
        }


        existingTask.setIsDeleted(true);
        taskRepository.save(existingTask);


        List<SubTask> subTasks = subTaskRepository.findByTaskIdAndDeletedFalse(taskId);
        for (SubTask subTask : subTasks) {
            subTask.setDeleted(true); // Soft delete subtask
            subTaskRepository.save(subTask);
        }
    }


}