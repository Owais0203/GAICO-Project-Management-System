package com.java.springBoot.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long duration;
    private Long cost;
    private Long budget;
    private Long projectID;

    @JsonIgnore
    @ManyToOne
    private Project project;
}
