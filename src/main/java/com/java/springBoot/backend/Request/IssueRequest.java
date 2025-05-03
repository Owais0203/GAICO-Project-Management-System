package com.java.springBoot.backend.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRequest {

    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String priority;
    private LocalDate endDate;
    private LocalDate startDate;
}
