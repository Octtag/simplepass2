package com.simple.simplepass.persistance.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
public class SimpleProductor implements UserDetails {

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

    public SimpleProductor(String username, String email, String dni, String celular, String password, SimpleUserRol appUserRole, Date fechaRegistro) {
        this.username = username;
        this.email = email;
        this.dni = dni;
        this.celular = celular;
        this.password = password;
        this.appUserRole = appUserRole;
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
