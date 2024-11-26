package com.example.projectTodoAPI.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or another strategy as needed
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    private String description;

    private int status = 0; // Default to 0 (Incomplete)

    private boolean deleted = false;

}
