package com.java.springBoot.backend.Request;

import lombok.Data;

@Data
public class StakeholderRequest {

    private Long projectID;
    private String name;
    private String role;
    private String contact;
    private String influence;
}
