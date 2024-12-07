package org.example.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioMessage implements Serializable {
    private Long promptId;
    private Long answerId;
    private String answerContent;
    private String keyword;
}

