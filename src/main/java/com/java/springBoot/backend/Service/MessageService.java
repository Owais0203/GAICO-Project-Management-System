package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;

    List<Message> getMessagesByProjectId(Long projectId) throws Exception;

}
