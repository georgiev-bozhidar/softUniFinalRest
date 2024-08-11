package org.georgievbozhidar.softunifinal2rest.service;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserInnerDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.UserRegisterDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateUserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.*;

import java.util.Set;

public interface UserService {
    public UserDTO getById(Long id);
    public UserDTO getByUsername(String username);
    public UserDTO getByEmail(String email);
    public UserInnerDTO getNoChainById(Long id);
    public UserInnerDTO getNoChainByUsername(String username);
    public UserInnerDTO getNoChainByEmail(String email);

    public User findById(Long id);
    public User findByUsername(String username);
    public User findByEmail(String email);

//    public void order(); TODO
//    public void checkout(); TODO

    public UserDTO createUser(@Valid UserRegisterDTO userRegisterDTO);

    public UserDTO updateUser(Long id, UpdateUserDTO updateUserDTO);

    public void deleteUser(Long id);

    public Set<UserDTO> getAllUsers();
}
