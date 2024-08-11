package org.georgievbozhidar.softunifinal2rest.init;

import org.georgievbozhidar.softunifinal2rest.entity.dto.create.UserRegisterDTO;
import org.georgievbozhidar.softunifinal2rest.repository.UserRepository;
import org.georgievbozhidar.softunifinal2rest.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Initialize implements CommandLineRunner {
    private final UserService userService;
    private final UserRepository userRepository;

    public Initialize(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initUsers();
    }

    private void initUsers() {
        if (userRepository.count() > 1){
            return;
        }

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("admin", "admin", "admin", LocalDate.of(2001, 1, 1));
        userService.createUser(userRegisterDTO);
    }
}
