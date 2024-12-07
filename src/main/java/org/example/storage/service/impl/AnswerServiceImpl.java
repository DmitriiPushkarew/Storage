package org.example.storage.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.storage.model.entity.Answer;
import org.example.storage.model.entity.Prompt;
import org.example.storage.repository.AnswerRepository;
import org.example.storage.repository.PromptRepository;
import org.example.storage.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final PromptRepository promptRepository;
    private final AnswerRepository answerRepository;

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

    public List<String> getKeywordsByTopic(String topic) {
        return answerRepository.findKeywordsByTopic(topic);
    }

    public Answer getAnswerByKeyword(String keyword) {
        return answerRepository.findByKeyword(keyword)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found for keyword: " + keyword));
    }
}
