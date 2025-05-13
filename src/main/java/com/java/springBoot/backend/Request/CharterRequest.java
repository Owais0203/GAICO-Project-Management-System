package com.java.springBoot.backend.Request;

import lombok.Data;

@Data
public class CharterRequest {

    private String title;
    private String description;
    private String sponsors;
    private String objective;
    private String deliverables;
    private Boolean approved;
    private Long projectID;
}
