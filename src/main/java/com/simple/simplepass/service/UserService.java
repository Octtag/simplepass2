package com.simple.simplepass.service;

import com.simple.simplepass.dto.PageResponseDTO;
import com.simple.simplepass.dto.SignUpRequest;
import com.simple.simplepass.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails createUser(SignUpRequest signUpRequest);

    PageResponseDTO<UserDTO> getUsers(Pageable pageable);
}
