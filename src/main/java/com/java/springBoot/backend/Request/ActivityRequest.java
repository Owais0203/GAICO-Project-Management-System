package com.java.springBoot.backend.Request;

import lombok.Data;

@Data
public class ActivityRequest {

    private String name;
    private Long duration;
    private Long cost;
    private Long budget;
    private Long projectID;
}
