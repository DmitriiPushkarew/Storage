package org.example.storage.dto;

import lombok.Data;

@Data
public class PromptAnswerRequest {
    private String prompt;
    private String answer;
}