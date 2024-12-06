package org.example.storage.service;

public interface PromptService {
    void addPrompt(String topic, String promptContent);

    void addAnswer(String promptTopic, String keyword, String answerContent);
}
