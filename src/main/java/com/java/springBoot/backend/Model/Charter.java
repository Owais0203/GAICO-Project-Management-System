package com.java.springBoot.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Charter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String sponsors;
    private String objective;
    private String deliverables;
    private Boolean approved;

    @JsonIgnore
    @OneToOne
    private Project project;
}