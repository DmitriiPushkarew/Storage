package org.example.storage.service;

import java.util.List;

public interface PromptService {
    void addPrompt(String topic, String promptContent);

    void addAnswer(String promptTopic, String keyword, String answerContent);

    List<String> getTopics();

    List<String> getKeywordsByTopic(String topic);

    void createVideo(String keyword);
}
