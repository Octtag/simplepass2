package com.simple.simplepass.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.simplepass.dto.SimpleUserDto;
import com.simple.simplepass.persistance.entities.SimpleProductor;
import com.simple.simplepass.persistance.entities.SimpleUser;
import com.simple.simplepass.persistance.entities.SimpleUserRol;
import com.simple.simplepass.persistance.repository.SimpleUserRepository;
import com.simple.simplepass.security.PasswordEncoder;
import com.simple.simplepass.service.SimpleUserService;
import com.simple.simplepass.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Date;

@RestController
@RequestMapping("/usuarios")
public class SimpleUserController {

    @Autowired
    SimpleUserService simpleUserService;

    @Autowired
    SimpleUserRepository simpleUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/index.html");
    }


    @PostMapping("/registrarUser")
    public ResponseEntity<?> registrarUsuario(@RequestBody SimpleUserDto simpleUserDto){
        if (simpleUserRepository.existsByEmail(simpleUserDto.getEmail())){
            return new ResponseEntity<>("Este email ya esta en uso, inicia sesion con tu cuenta", HttpStatus.BAD_REQUEST);
        }
        SimpleUser usuario = new SimpleUser();
        usuario.setUsername(simpleUserDto.getUsername());
        usuario.setEmail(simpleUserDto.getEmail());
        usuario.setDni(simpleUserDto.getDni());
        usuario.setCelular(simpleUserDto.getCelular());
        usuario.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(simpleUserDto.getPassword()));
        usuario.setAppUserRole(SimpleUserRol.valueOf("USER"));

        simpleUserRepository.save(usuario);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    @PostMapping("/registrarProductor")
    public ResponseEntity<?> registrarProductor(@RequestBody SimpleProductor simpleProductor){
        if (simpleUserRepository.existsByEmail(simpleProductor.getEmail())){
            return new ResponseEntity<>("Este email ya esta en uso, inicia sesion con tu cuenta", HttpStatus.BAD_REQUEST);
        }
        SimpleUser usuario = new SimpleUser();
        usuario.setUsername(simpleProductor.getUsername());
        usuario.setEmail(simpleProductor.getEmail());
        usuario.setDni(simpleProductor.getDni());
        usuario.setCelular(simpleProductor.getCelular());
        usuario.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(simpleProductor.getPassword()));
        usuario.setAppUserRole(SimpleUserRol.valueOf("ADMIN"));

        simpleUserRepository.save(usuario);
        return new ResponseEntity<>("Productor registrado exitosamente", HttpStatus.OK);
    }

    @PostMapping("/addUsuario")
    public ResponseEntity<SimpleUserDto> addSimpleUser(@RequestBody SimpleUser simpleUser){

        ResponseEntity<SimpleUserDto> response = null;
        ObjectMapper mapper = new ObjectMapper();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(simpleUser.getPassword());

        simpleUser.setFechaRegistro(Util.utilDateToSqlDate(Util.dateToTimestamp(new Date())));
        simpleUser.setPassword(hashedPassword);

        SimpleUser simpleUser1 = simpleUserService.addSimpleUser(simpleUser);
        SimpleUserDto simpleUserDto = null;
        simpleUserDto = mapper.convertValue(simpleUser1, SimpleUserDto.class);

        return ResponseEntity.ok(simpleUserDto);
    }
}
