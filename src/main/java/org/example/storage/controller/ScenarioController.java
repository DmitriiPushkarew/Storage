package org.example.storage.controller;

import lombok.RequiredArgsConstructor;
import org.example.storage.request.AnswerRequest;
import org.example.storage.request.PromptRequest;
import org.example.storage.service.impl.AnswerServiceImpl;
import org.example.storage.service.impl.PromptServiceImpl;
import org.example.storage.service.impl.ScenarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scenario")
public class ScenarioController {
    private final PromptServiceImpl promptService;
    private final AnswerServiceImpl answerService;
    private final ScenarioServiceImpl scenarioService;

    @GetMapping("/topics")
    public ResponseEntity<List<String>> getTopics() {
        return ResponseEntity.ok(promptService.getTopics());
    }

    @GetMapping("/keywords")
    public List<String> getKeywordsByTopic(@RequestParam(name = "topic") String topic) {
        return answerService.getKeywordsByTopic(topic);
    }

    @PostMapping("/prompt")
    public ResponseEntity<Void> addPrompt(@RequestBody PromptRequest request) {
        promptService.addPrompt(request.getTopic(), request.getContent());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/answer")
    public ResponseEntity<Void> addAnswer(@RequestBody AnswerRequest request) {
        answerService.addAnswer(request.getPromptTopic(), request.getKeyword(), request.getContent());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-video")
    public ResponseEntity<Void> createVideo(@RequestParam(name = "keyword") String keyword) {
        scenarioService.createVideo(keyword);
        return ResponseEntity.ok().build();
    }
}