package org.example.storage.service;

import java.util.List;

public interface PromptService {

    void addPrompt(String topic, String promptContent);

    List<String> getTopics();
}
