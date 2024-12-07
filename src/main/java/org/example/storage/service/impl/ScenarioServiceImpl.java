package org.example.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.storage.dto.ScenarioMessageDto;
import org.example.storage.model.entity.Answer;
import org.example.storage.service.ScenarioService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {
    private final AnswerServiceImpl answerService;
    private final RabbitTemplate rabbitTemplate;

    public void createVideo(String keyword) {
        Answer answer = answerService.getAnswerByKeyword(keyword);
        ScenarioMessageDto scenarioMessage = ScenarioMessageDto.builder()
                .promptId(answer.getPrompt().getId())
                .answerId(answer.getId())
                .answerContent(answer.getContent())
                .keyword(answer.getKeyword())
                .build();
        rabbitTemplate.convertAndSend("scenario_queue", scenarioMessage);
    }
}