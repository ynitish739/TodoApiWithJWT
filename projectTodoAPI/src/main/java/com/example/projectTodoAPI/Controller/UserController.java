package com.example.projectTodoAPI.Controller;

import com.example.projectTodoAPI.Entity.app_User;
import com.example.projectTodoAPI.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody app_User user) {
        app_User createdUser = userService.addUser(user.getUsername(), user.getEmail());
        return ResponseEntity.status(201).body(createdUser); // Return created user
    }

    @GetMapping
    public ResponseEntity<List<app_User>> getAllUsers() {
        List<app_User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<app_User> getUserById(@PathVariable Long userId) {
        app_User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}