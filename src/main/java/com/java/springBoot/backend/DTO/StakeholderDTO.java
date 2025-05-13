package com.java.springBoot.backend.DTO;

import com.java.springBoot.backend.Model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StakeholderDTO {

    private Long id;

    private String name;
    private String role;
    private String contact;
    private String influence;
    private Long projectID;
    private Project project;
}
