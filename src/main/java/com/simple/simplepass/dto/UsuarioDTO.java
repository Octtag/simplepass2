package com.simple.simplepass.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {

    private String username;

    private String email;

    private String dni;

    private String celular;

    private String password;
}
