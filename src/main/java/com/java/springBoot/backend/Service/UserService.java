package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Users;

public interface UserService {

    Users findUserProfileByJwt(String jwt) throws Exception;

    Users findUserByEmail(String email) throws Exception;

    Users findUserById(Long id) throws Exception;

    Users updateUserProjectSize(Users user, int number) throws Exception;
}
