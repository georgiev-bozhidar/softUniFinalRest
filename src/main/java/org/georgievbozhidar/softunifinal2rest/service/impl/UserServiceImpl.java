package org.georgievbozhidar.softunifinal2rest.service.impl;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserRegisterDTO;
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
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()){
            throw new UserNotFoundException();
        }

        return optUser.get();
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()){
            throw new UserNotFoundException();
        }

        userRepository.delete(optUser.get());
    }

    @Override
    public UserDTO createUser(@Valid UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setBirthday(userRegisterDTO.getBirthday());
        userRepository.save(user);
        User user2 = userRepository.findByUsername(user.getUsername()).get();
        UserDTO userDTO = modelMapper.map(user2, UserDTO.class);

        return userDTO;
    }

//    @Override
//    public UserDTO updateUsername(Long id, UserDTO userDTO) {
//        User user = userRepository.findById(id).get();
//        user.setUsername(userDTO.getUsername());
//        userRepository.save(user);
//        return modelMapper.map(userDTO, UserDTO.class);
//    }
}
