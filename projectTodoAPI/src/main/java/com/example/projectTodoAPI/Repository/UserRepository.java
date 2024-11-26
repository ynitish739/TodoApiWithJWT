package com.example.projectTodoAPI.Repository;

import com.example.projectTodoAPI.Entity.app_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<app_User,Long> {
}
