package org.example.storage.service;

import org.example.storage.model.entity.Answer;

import java.util.List;

public interface AnswerService {

    void addAnswer(String promptTopic, String keyword, String answerContent);

    List<String> getKeywordsByTopic(String topic);

    Answer getAnswerByKeyword(String keyword);

}
