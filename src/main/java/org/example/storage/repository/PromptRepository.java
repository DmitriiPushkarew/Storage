package org.example.storage.repository;


import org.example.storage.model.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, Long> {

    Optional<Prompt> findByTopic(String topic);

    @Query("SELECT DISTINCT p.topic FROM Prompt p")
    List<String> findAllDistinctTopics();
}