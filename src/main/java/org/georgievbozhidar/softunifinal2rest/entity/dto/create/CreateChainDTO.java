package org.georgievbozhidar.softunifinal2rest.entity.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;

public class CreateChainDTO {
    @NotBlank(message = "Chain name must not be blank.")
    private String name;

    @JsonProperty("owner")
    private UserDTO owner;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
