package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Chat;
import com.java.springBoot.backend.Model.Message;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Repository.MessageRepository;
import com.java.springBoot.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        Users sender = userRepository.findById(senderId).orElseThrow(() -> new Exception("User not found"));

        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setSender(sender);
        message.setChat(chat);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getProjectById(projectId).getChat();
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
    }
}
