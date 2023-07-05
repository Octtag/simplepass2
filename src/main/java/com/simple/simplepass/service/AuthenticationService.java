package com.simple.simplepass.service;

import com.simple.simplepass.dto.AuthenticationResponse;
import com.simple.simplepass.dto.LoginRequest;
import com.simple.simplepass.dto.SignUpRequest;

public interface AuthenticationService {
    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse signUp(SignUpRequest signUpRequest);

}
