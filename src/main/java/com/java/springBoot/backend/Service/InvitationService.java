package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Invitation;

public interface InvitationService {

    public void sendInvitation(String email, Long projectId) throws Exception;

    public Invitation acceptInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserMail(String userEmail) throws Exception;

    void deleteToken(String token) throws Exception;
}
