package com.belikov.valteris.cycle.user.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.user.model.User;
import com.belikov.valteris.cycle.user.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, User> {

    @Override
    public UserDTO mapEntityToDomain(User entity) {
        if (entity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getUsername());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPassword(entity.getPassword());
        return userDTO;
    }

    @Override
    public User mapDomainToEntity(UserDTO domain) {
        if (domain == null) {
            return null;
        }
        User user = new User();
        user.setId(domain.getId());
        user.setUsername(domain.getUsername());
        user.setEmail(domain.getEmail());
        user.setPassword(domain.getPassword());
        return user;
    }
}

