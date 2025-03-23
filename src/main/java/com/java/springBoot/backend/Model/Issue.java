package com.java.springBoot.backend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users assignee;
}
