package com.example.projectTodoAPI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> getHomePage() {
        // Simulating user claims that would be in the payload
        String userId = "12345";  // Unique identifier for the user
        String role = "USER";     // Role of the user (can be USER, ADMIN, etc.)
        long exp = 1700937600;    // Expiration time (Unix timestamp in seconds)

        // Constructing a simple HTML page to display the claims
        String htmlResponse = "<html>"
                + "<body>"
                + "  \"userId\": \"" + userId + "\",<br>"
                + "  \"role\": \"" + role + "\",<br>"
                + "  \"exp\": " + exp + "<br>"
                + "}<br>"
                + "</body>"
                + "</html>";

        return ResponseEntity.ok(htmlResponse);
    }
}