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
    public void addPromptAndAnswer(String promptContent, String answerContent) {
        Prompt prompt = Prompt.builder()
                .content(promptContent)
                .build();
        prompt = promptRepository.save(prompt);

        Answer answer = Answer.builder()
                .prompt(prompt)
                .answer(answerContent)
                .build();
        answerRepository.save(answer);
    }
}
