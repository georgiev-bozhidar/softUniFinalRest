package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserRegisterDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.User;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        try {
            User user = userService.getById(id);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException(unfe.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO) throws Exception {
        try {
            UserDTO userDTO = userService.createUser(userRegisterDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUsername(@RequestBody @Valid UserDTO userDTO, @PathVariable Long id) throws Exception {
//        try {
//            UserDTO updatedUserDTO = userService.updateUsername(userDTO);
//            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
//        } catch (UserNotFoundException unfe){
//            throw new UserNotFoundException(unfe.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException(unfe.getMessage());
        }
    }
}
