package org.makar.t1_tasks.controller;

import lombok.RequiredArgsConstructor;
import org.makar.t1_tasks.dto.LoginRequest;
import org.makar.t1_tasks.dto.LoginResponse;
import org.makar.t1_tasks.dto.SignupRequest;
import org.makar.t1_tasks.dto.SignupResponse;
import org.makar.t1_tasks.service.UserService;
import org.makar.t1_tasks.utils.AuthMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    public SignupResponse register(@RequestBody SignupRequest signupRequest) {
        return new SignupResponse(userService.register(
                authMapper.convertFromSignupRequestToUser(signupRequest),
                signupRequest.getRoleEnums()
        ));
    }

    @PostMapping("/auth")
    public LoginResponse authenticate(@RequestBody LoginRequest loginRequest) {
        return userService.authenticate(authMapper.convertFromLoginRequestToUser(loginRequest));
    }



}
