package com.java.springBoot.backend.DTO;

import com.java.springBoot.backend.Model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
    private Long id;

    private String name;
    private Long duration;
    private Long cost;
    private Long budget;
    private Long projectID;
    private Project project;
}
