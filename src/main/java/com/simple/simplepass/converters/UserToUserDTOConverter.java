package com.simple.simplepass.converters;

import com.simple.simplepass.dto.UserDTO;
import com.simple.simplepass.model.SimpleUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTOConverter implements Converter<SimpleUser, UserDTO> {

    @Override
    public UserDTO convert(SimpleUser source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(source.getEmail());
        userDTO.setRole(source.getRole().name());
        return userDTO;
    }
}
