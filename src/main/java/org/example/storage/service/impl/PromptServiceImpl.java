package org.example.storage.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.storage.model.entity.Prompt;
import org.example.storage.repository.PromptRepository;
import org.example.storage.service.PromptService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {
    private final PromptRepository promptRepository;

    @Transactional
    public void addPrompt(String topic, String promptContent) {
        Prompt prompt = Prompt.builder()
                .content(promptContent)
                .topic(topic)
                .build();

        promptRepository.save(prompt);
    }

    public List<String> getTopics() {
        return promptRepository.findAllDistinctTopics();
    }

}
