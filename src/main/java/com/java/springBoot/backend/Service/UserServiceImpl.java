package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Config.JwtProvider;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public Users findUserByEmail(String email) throws Exception {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found!!!");
        }
        return user;
    }

    @Override
    public Users findUserById(Long id) throws Exception {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found!!!");
        }
        return optionalUser.get();
    }

    @Override
    public Users updateUserProjectSize(Users user, int number) throws Exception {
        user.setProjectSize(user.getProjectSize() + number);
        if (user.getProjectSize() < 0) {
            throw new Exception("Project size cannot be negative!!!");
        }
        return userRepository.save(user);
    }
}
