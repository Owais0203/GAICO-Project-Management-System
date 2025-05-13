package com.java.springBoot.backend.DTO;


import com.java.springBoot.backend.Model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharterDTO {
    private Long id;

    private String title;
    private String description;
    private String sponsors;
    private String objective;
    private String deliverables;
    private Boolean approved;
    private Long projectID;
    private Project project;
}
