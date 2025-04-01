package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.Model.Chat;
import com.java.springBoot.backend.Model.Message;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Request.CreateMessageRequest;
import com.java.springBoot.backend.Service.MessageService;
import com.java.springBoot.backend.Service.ProjectService;
import com.java.springBoot.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest messageRequest) throws Exception {
        Users user = userService.findUserById(messageRequest.getSenderId());
        if(user == null) {
            throw new Exception("User not found with id: " + messageRequest.getSenderId());
        }

        Chat chat = projectService.getProjectById(messageRequest.getProjectId()).getChat();
        if(chat == null) {
            throw new Exception("Chat not found with project id: " + messageRequest.getProjectId());
        }
        Message sentMessage = messageService.sendMessage(messageRequest.getSenderId(), messageRequest.getProjectId(), messageRequest.getContent());
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
