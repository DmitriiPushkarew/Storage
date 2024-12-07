package org.example.storage.repository;

import org.example.storage.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a.keyword FROM Answer a WHERE a.prompt.topic = :topic")
    List<String> findKeywordsByTopic(String topic);

    @Query("SELECT a FROM Answer a WHERE a.keyword = :keyword")
    Optional<Answer> findByKeyword(String keyword);
}
