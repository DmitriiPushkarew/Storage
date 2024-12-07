package org.example.storage.controller;

import lombok.RequiredArgsConstructor;
import org.example.storage.dto.request.AnswerRequest;
import org.example.storage.dto.request.PromptRequest;
import org.example.storage.service.PromptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @PostMapping("/addPrompt")
    public ResponseEntity<String> addPrompt(@RequestBody PromptRequest request) {
        promptService.addPrompt(request.getTopic(), request.getContent());
        return ResponseEntity.ok("Prompt added successfully!");
    }

    @PostMapping("/addAnswer")
    public ResponseEntity<String> addAnswer(@RequestBody AnswerRequest request) {
        promptService.addAnswer(request.getPromptTopic(), request.getKeyword(), request.getContent());
        return ResponseEntity.ok("Answer added successfully!");
    }

    @GetMapping("/topics")
    public List<String> getTopics() {
        return promptService.getTopics();
    }

    @GetMapping("/keywords")
    public List<String> getKeywordsByTopic(@RequestParam(name = "topic") String topic) {
        return promptService.getKeywordsByTopic(topic);
    }

    @GetMapping("/create-video")
    public String createVideo(@RequestParam(name = "keyword") String keyword) {
        promptService.createVideo(keyword);
        return "Video creation request sent for keyword: " + keyword;
    }
}
