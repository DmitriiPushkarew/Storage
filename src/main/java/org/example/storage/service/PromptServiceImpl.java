package org.example.storage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.storage.model.ScenarioMessage;
import org.example.storage.model.entity.Answer;
import org.example.storage.model.entity.Prompt;
import org.example.storage.repository.AnswerRepository;
import org.example.storage.repository.PromptRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptRepository promptRepository;
    private final AnswerRepository answerRepository;
    private final RabbitTemplate rabbitTemplate;


    @Transactional
    public void addPrompt(String topic, String promptContent) {
        Prompt prompt = Prompt.builder()
                .content(promptContent)
                .topic(topic)
                .build();
        promptRepository.save(prompt);
    }

    @Transactional
    public void addAnswer(String topic, String keyword, String answerContent) {
        Prompt prompt = promptRepository.findByTopic(topic)
                .orElseThrow(() -> new IllegalArgumentException("Prompt not found with topic: " + topic));
        Answer answer = Answer.builder()
                .prompt(prompt)
                .keyword(keyword)
                .content(answerContent)
                .build();
        answerRepository.save(answer);
    }

    public List<String> getTopics() {
        return promptRepository.findAllDistinctTopics();
    }

    public List<String> getKeywordsByTopic(String topic) {
        return answerRepository.findKeywordsByTopic(topic);
    }

    @Override
    public void createVideo(String keyword) {
        Answer answer = answerRepository.findByKeyword(keyword)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found for keyword: " + keyword));
        ScenarioMessage scenarioMessage = new ScenarioMessage(
                answer.getPrompt().getId(),
                answer.getId(),
                answer.getContent(),
                keyword
        );
        rabbitTemplate.convertAndSend("scenario_queue", scenarioMessage);
    }

}
