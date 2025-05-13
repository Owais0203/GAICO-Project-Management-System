package com.java.springBoot.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StakeHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String role;
    private String contact;
    private String influence;
    private Long projectID;

    @JsonIgnore
    @ManyToOne
    private Project project;
}
