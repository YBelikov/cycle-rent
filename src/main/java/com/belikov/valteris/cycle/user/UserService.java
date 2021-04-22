package com.belikov.valteris.cycle.user;

import com.belikov.valteris.cycle.user.model.UserDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationProvider;

public interface UserService extends AuthenticationProvider {

    void save(UserDTO user);

    List<UserDTO> getAll();

    Optional<UserDTO> getById(Long id);

    void delete(Long id);

    boolean register(UserDTO userDTO);

    Optional<UserDTO> findByUsername(String username);
}