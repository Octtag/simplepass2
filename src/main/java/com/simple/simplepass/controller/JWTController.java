package com.simple.simplepass.controller;

import com.simple.simplepass.persistance.entities.AuthenticationResponse;
import com.simple.simplepass.persistance.entities.SimpleUser;
import com.simple.simplepass.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SimpleUser simpleUser) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(simpleUser.getEmail(), simpleUser.getPassword()));
        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(simpleUser.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse((jwt)));

    }
}
