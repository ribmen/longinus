package com.imd.longinus.controller;

import com.imd.longinus.service.PromptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prompt")
public class PromptController {
    private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/{mesage}")
    public ResponseEntity<String> prompt(@PathVariable String mesage) {
        return ResponseEntity.ok(promptService.prompt(mesage));
    }

}
