package org.example.storage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.storage.model.Answer;
import org.example.storage.model.Prompt;
import org.example.storage.repository.AnswerRepository;
import org.example.storage.repository.PromptRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptRepository promptRepository;
    private final AnswerRepository answerRepository;

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
}