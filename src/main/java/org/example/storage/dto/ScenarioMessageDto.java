package org.example.storage.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ScenarioMessageDto implements Serializable {
    private Long promptId;
    private Long answerId;
    private String answerContent;
    private String keyword;

}


