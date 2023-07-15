package com.simple.simplepass.service;

import com.simple.simplepass.persistance.entities.SimpleProductor;
import com.simple.simplepass.persistance.entities.SimpleUser;
import com.simple.simplepass.persistance.repository.SimpleProductorRepository;
import com.simple.simplepass.persistance.repository.SimpleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService implements UserDetailsService {

    @Autowired
    SimpleUserRepository userRepository;
    @Autowired
    SimpleProductorRepository simpleProductorRepository;

    @Override
    public UserDetails loadUserByUsername(String email){

        return userRepository.findByEmail(email).get();

    }

    public SimpleProductor addSimpleProductor(SimpleProductor simpleProductor) { return  simpleProductorRepository.save(simpleProductor);}

    public SimpleUser addSimpleUser(SimpleUser simpleUser){
        return userRepository.save(simpleUser);
    }

}
