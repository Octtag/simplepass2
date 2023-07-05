package com.simple.simplepass.service;

import com.simple.simplepass.dto.AuthenticationResponse;
import com.simple.simplepass.dto.LoginRequest;
import com.simple.simplepass.dto.SignUpRequest;
import com.simple.simplepass.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        String jwt =jwtService
                .generateToken((UserDetails) authentication.getPrincipal());

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        UserDetails userDetails = userService.createUser(signUpRequest);
        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(userDetails))
                .build();
    }
}
