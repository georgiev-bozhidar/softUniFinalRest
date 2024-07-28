package org.georgievbozhidar.softunifinal2rest.entity.dto.update;

import jakarta.validation.constraints.*;
import org.georgievbozhidar.softunifinal2rest.validation.annotation.UniqueEmail;
import org.georgievbozhidar.softunifinal2rest.validation.annotation.UniqueUsername;

import java.time.LocalDate;

public class UpdateUserDTO {
    @Size(min = 2, max = 32, message = "Username must be between 2 and 32 characters long.")
    @UniqueUsername(message = "Username is already in use.")
    private String username;

    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters long.")
    private String password;

    @Email(message = "Invalid email format.")
    @UniqueEmail(message = "Email is already in use.")
    private String email;

    @PastOrPresent(message = "Invalid date.")
    private LocalDate birthday;

    public @Size(min = 2) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 2) String username) {
        this.username = username;
    }

    public @Size(min = 8) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8) String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @PastOrPresent LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(@PastOrPresent LocalDate birthday) {
        this.birthday = birthday;
    }
}
