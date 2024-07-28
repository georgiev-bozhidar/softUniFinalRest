package org.georgievbozhidar.softunifinal2rest.service.impl;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.UserRegisterDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.*;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.repository.*;
import org.georgievbozhidar.softunifinal2rest.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return optUser.get();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return optUser.get();
    }

    @Override
    public User findById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return optUser.get();
    }

    @Override
    public UserDTO getByUsername(String username) {
        return modelMapper.map(this.findByUsername(username), UserDTO.class);
    }

    @Override
    public UserDTO getByEmail(String email) {
        return modelMapper.map(this.findByEmail(email), UserDTO.class);
    }

    @Override
    public UserDTO getById(Long id) {
        return modelMapper.map(this.findById(id), UserDTO.class);
    }

    @Override
    public UserDTO createUser(@Valid UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(user);

        return this.getByUsername(user.getUsername());
    }

    @Override
    public UserDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = this.findById(id);
        if (updateUserDTO.getUsername() != null) user.setUsername(updateUserDTO.getUsername());
        if (updateUserDTO.getPassword() != null) user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        if (updateUserDTO.getEmail() != null) user.setEmail(updateUserDTO.getEmail());
        if (updateUserDTO.getBirthday() != null) user.setBirthday(updateUserDTO.getBirthday());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(this.findById(id));
    }
}
