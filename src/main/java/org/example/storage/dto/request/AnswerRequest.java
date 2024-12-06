package org.example.storage.dto.request;

import lombok.Data;

@Data
public class AnswerRequest {
    private String promptTopic;
    private String keyword;
    private String content;
}
