package com.tasks.core.tasks.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    
    private String description;

    private Boolean completed;

    private Date createDate;

    private Date dateCompleted;

    public Task(String title, String description, Boolean completed, Date createDate, Date dateCompleted) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createDate = createDate;
        this.dateCompleted = dateCompleted;
    }

}
