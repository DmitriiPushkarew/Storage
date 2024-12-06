package org.example.storage.controller;

import lombok.RequiredArgsConstructor;
import org.example.storage.dto.request.AnswerRequest;
import org.example.storage.dto.request.PromptRequest;
import org.example.storage.service.PromptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
