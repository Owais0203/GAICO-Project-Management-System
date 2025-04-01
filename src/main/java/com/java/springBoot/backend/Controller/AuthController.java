package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.Config.JwtProvider;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Repository.UserRepository;
import com.java.springBoot.backend.Request.LoginRequest;
import com.java.springBoot.backend.Response.AuthResponse;
import com.java.springBoot.backend.Service.CustomUserDetailsImpl;
import com.java.springBoot.backend.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Users user) throws Exception {

        Users isUserExist = userRepository.findByEmail(user.getEmail());

        if (isUserExist != null) {
            throw new RuntimeException("User already exist with this email!!!");
        }

        Users createdUser = new Users();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        Users savedUser = userRepository.save(createdUser);

        subscriptionService.createSubscription(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Signup successful!!!");
        authResponse.setJwt(jwt);

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Sign-in successful!!!");
        authResponse.setJwt(jwt);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("User not found with this email!!!" + username);
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password!!!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
