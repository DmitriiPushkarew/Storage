package org.example.storage.request;

import lombok.Data;

@Data
public class AnswerRequest {
    private String promptTopic;
    private String keyword;
    private String content;

}
