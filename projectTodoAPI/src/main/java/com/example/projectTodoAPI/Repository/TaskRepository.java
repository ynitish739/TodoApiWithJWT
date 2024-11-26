package com.example.projectTodoAPI.Repository;

import com.example.projectTodoAPI.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUserIdAndIsDeletedFalse(Long userId);
    List<Task> findByUserIdAndPriorityAndIsDeletedFalse(Long userId, int priority);
    List<Task> findByUserIdAndStatusAndIsDeletedFalse(Long userId, int status);
}
