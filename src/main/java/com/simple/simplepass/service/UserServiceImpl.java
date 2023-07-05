package com.simple.simplepass.service;

import com.simple.simplepass.dto.PageResponseDTO;
import com.simple.simplepass.dto.SignUpRequest;
import com.simple.simplepass.dto.UserDTO;
import com.simple.simplepass.model.SimpleUser;
import com.simple.simplepass.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import static com.simple.simplepass.model.Role.USER;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        SimpleUser simpleUser = userRepository.findByEmail(email).get();

        if (simpleUser == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return simpleUser;
    }

    @Override
    public UserDetails createUser(SignUpRequest signUpRequest) {
        try{
            return userRepository.save(SimpleUser.builder()
                    .email(signUpRequest.getEmail())
                    .username(signUpRequest.getUsername())
                    .dni(signUpRequest.getDni())
                    .celular(signUpRequest.getCelular())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .enabled(true)
                    .role(USER)
                    .build());
        }catch (DataIntegrityViolationException e){
            throw new ErrorResponseException(HttpStatus.CONFLICT,
                    ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                            "User already exist"),e);
        }
    }

    @Override
    public PageResponseDTO<UserDTO> getUsers(Pageable pageable) {

        Page<SimpleUser> userPage = userRepository.findAll(pageable);
        return new PageResponseDTO<>(
                userPage.getContent().stream()
                        .map(user -> conversionService.convert(user, UserDTO.class)).toList()
                , userPage.getPageable()
                , userPage.getTotalElements());
    }
}
