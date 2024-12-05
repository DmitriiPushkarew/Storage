package org.example.storage.controller;

import lombok.RequiredArgsConstructor;
import org.example.storage.dto.PromptAnswerRequest;
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

    @PostMapping("/addPromptAndAnswer")
    public ResponseEntity<String> addPromptAndAnswer(@RequestBody PromptAnswerRequest request) {
        promptService.addPromptAndAnswer(request.getPrompt(), request.getAnswer());
        return ResponseEntity.ok("Prompt and Answer added successfully!");
    }

}
