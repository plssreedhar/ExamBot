package com.scaler.exambot.controllers;


import com.scaler.exambot.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        return chatService.chat(prompt);
    }
}
