package com.example.projectTodoAPI.Service;

import com.example.projectTodoAPI.Entity.app_User;
import com.example.projectTodoAPI.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch a user by ID
//    @Autowired
//    private UserRepository userRepository;


    public app_User addUser(String username, String email) {
        app_User user = new app_User();
        user.setUsername(username);
        user.setEmail(email);
        return userRepository.save(user);
    }


    public List<app_User> getAllUsers() {
        return userRepository.findAll();
    }
    public app_User getUserById(Long userId) {
        try {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user: " + e.getMessage());
        }
    }
}