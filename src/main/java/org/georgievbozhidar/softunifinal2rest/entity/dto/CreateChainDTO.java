package org.georgievbozhidar.softunifinal2rest.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.georgievbozhidar.softunifinal2rest.entity.model.User;

public class CreateChainDTO {
    @NotBlank(message = "Chain name must not be blank.")
    private String name;

    @JsonProperty("owner")
    private User owner;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
