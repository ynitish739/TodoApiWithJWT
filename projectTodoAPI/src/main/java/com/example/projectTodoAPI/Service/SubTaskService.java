package com.example.projectTodoAPI.Service;


import com.example.projectTodoAPI.Entity.Task;
import com.example.projectTodoAPI.Entity.SubTask;
import com.example.projectTodoAPI.Repository.SubTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;

    private final TaskService taskService;
    private final UserService userService;

    public SubTaskService(SubTaskRepository subTaskRepository, TaskService taskService, UserService userService) {
        this.subTaskRepository = subTaskRepository;
        this.taskService = taskService;
        this.userService = userService;
    }


    public SubTask createSubTask(Long taskId, SubTask subTask,Long userId) {
        try {
            // Validate task existence
            Task task = taskService.getTaskById(taskId,userId);
            subTask.setTask(task);
            return subTaskRepository.save(subTask);
        } catch (Exception e) {
            throw new RuntimeException("Error creating subtask: " + e.getMessage());
        }
    }


    public List<SubTask> getSubTasks(Long taskId,Long userId) {
        try {

            taskService.getTaskById(taskId,userId); // Ensures the task exists
            return subTaskRepository.findByTaskIdAndDeletedFalse(taskId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching subtasks for task ID " + taskId + ": " + e.getMessage());
        }
    }

    public SubTask updateSubTask(Long subTaskId, SubTask subTask, Long userId) {
        SubTask existingSubTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new RuntimeException("Subtask not found"));

        if (!existingSubTask.getTask().getUser().getId().equals(userId)) {
            throw new RuntimeException("User is not authorized to update this subtask");
        }

        existingSubTask.setDescription(subTask.getDescription());
        existingSubTask.setStatus(subTask.getStatus());
        return subTaskRepository.save(existingSubTask);
    }
    public void deleteSubTask(Long subTaskId, Long userId) {
        SubTask existingSubTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new RuntimeException("Subtask not found"));

        if (!existingSubTask.getTask().getUser().getId().equals(userId)) {
            throw new RuntimeException("User is not authorized to delete this subtask");
        }

        existingSubTask.setDeleted(true);
        subTaskRepository.save(existingSubTask);
    }

}
