package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;

import java.time.LocalDate;
import java.util.Set;

public class UserDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @PastOrPresent
    private LocalDate birthday;

    private Set<Chain> ownedChains;

    private Set<Chain> favouriteChains;

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public Set<Chain> getOwnedChains() {
        return ownedChains;
    }

    public void setOwnedChains(Set<Chain> ownedChains) {
        this.ownedChains = ownedChains;
    }

    public Set<Chain> getFavouriteChains() {
        return favouriteChains;
    }

    public void setFavouriteChains(Set<Chain> favouriteChains) {
        this.favouriteChains = favouriteChains;
    }

    public @NotBlank Long getId() {
        return id;
    }

    public void setId(@NotBlank Long id) {
        this.id = id;
    }

    public @PastOrPresent LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(@PastOrPresent LocalDate birthday) {
        this.birthday = birthday;
    }
}
