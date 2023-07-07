package com.simple.simplepass.security;

import com.simple.simplepass.persistance.entities.SimpleUser;
import com.simple.simplepass.persistance.entities.SimpleUserRol;
import com.simple.simplepass.persistance.repository.SimpleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private SimpleUserRepository userRepository;

    @Autowired
    public DataLoader(SimpleUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");
        userRepository.save(new SimpleUser("Diego", "diego@digital.com", "42365264", "3434658344",hashedPassword, SimpleUserRol.USER));
        userRepository.save(new SimpleUser("Fabio", "fabio@digital.com", "34585361", "3434658344",hashedPassword2, SimpleUserRol.ADMIN));
    }
}