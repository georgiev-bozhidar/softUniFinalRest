package org.georgievbozhidar.softunifinal2rest.service;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserRegisterDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.*;

import java.util.Optional;

public interface UserService {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);

//    public void order();
//    public void checkout();

    User getById(Long id);

    void deleteUser(Long id);
    UserDTO createUser(@Valid UserRegisterDTO userRegisterDTO);

//    UserDTO updateUsername(Long id, UserDTO userDTO);
}
