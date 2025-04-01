package com.java.springBoot.backend.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {

    private Long issueId;

    private String content;
}
