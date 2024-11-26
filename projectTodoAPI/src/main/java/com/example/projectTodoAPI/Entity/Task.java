package com.example.projectTodoAPI.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private app_User user;

    private String title;

    private LocalDate dueDate;

    private int priority; // int because - Priority: 1 = High, 2 = Medium, 3 = Low

    private int status=0;  //same way - Status: 0 = Notdone, 1 = Done

    private Boolean isDeleted=false;

}
