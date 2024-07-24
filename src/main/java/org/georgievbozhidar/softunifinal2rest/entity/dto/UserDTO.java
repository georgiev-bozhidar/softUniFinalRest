package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;
import org.georgievbozhidar.softunifinal2.entity.model.Chain;

import java.util.Set;

public class UserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

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
}
