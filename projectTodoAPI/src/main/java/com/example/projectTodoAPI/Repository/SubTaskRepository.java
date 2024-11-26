package com.example.projectTodoAPI.Repository;

import com.example.projectTodoAPI.Entity.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    List<SubTask> findByTaskIdAndDeletedFalse(Long taskId);
    List<SubTask> findAllByDeletedFalse();
}
