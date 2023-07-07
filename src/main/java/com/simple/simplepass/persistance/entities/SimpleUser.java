package com.simple.simplepass.persistance.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
public class SimpleUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private String email;

    private String dni;

    private String celular;

    private String password;

    @Enumerated(EnumType.STRING)
    private SimpleUserRol appUserRole;

    private Date fechaRegistro;

    public SimpleUser(String username, String email, String dni, String celular, String password, SimpleUserRol appUserRole) {
        this.username = username;
        this.email = email;
        this.dni = dni;
        this.celular = celular;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public SimpleUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

