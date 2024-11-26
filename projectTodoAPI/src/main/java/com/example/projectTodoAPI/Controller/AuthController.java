package com.example.projectTodoAPI.Controller;

import com.example.projectTodoAPI.Extra.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestParam Long userId) {
        try {
            String token = jwtUtil.generateToken(userId);
            return ResponseEntity.ok("Generated Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating token: " + e.getMessage());
        }
    }
}