package org.example.storage.dto.request;

import lombok.Data;

@Data
public class PromptRequest {
    private String topic;
    private String content;
}