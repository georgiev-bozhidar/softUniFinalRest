package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.UserRegisterDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateUserDTO;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.georgievbozhidar.softunifinal2rest.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ChainService chainService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ChainService chainService, ModelMapper modelMapper) {
        this.userService = userService;
        this.chainService = chainService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        try {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException(unfe.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO) throws Exception {
        try {
            return new ResponseEntity<>(userService.createUser(userRegisterDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/{userId}/favourite/{chainId}")
    public ResponseEntity<UserDTO> addChainToFavourites(@PathVariable("userId") Long userId, @PathVariable("chainId") Long chainId){
        return new ResponseEntity<>(chainService.addToFavourites(userId, chainId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/favourite/{chainId}")
    public ResponseEntity<Void> removeChainFromFavourites(@PathVariable("userId") Long userId, @PathVariable("chainId") Long chainId) {
        chainService.removeFromFavourites(userId, chainId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UpdateUserDTO updateUserDTO, @PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(userService.updateUser(id, updateUserDTO), HttpStatus.OK);
        } catch (UserNotFoundException unfe){
            throw new UserNotFoundException(unfe.getMessage());
        }
    }

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
