package com.java.springBoot.backend.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {

    private Long senderId;

    private String content;

    private Long projectId;
}
