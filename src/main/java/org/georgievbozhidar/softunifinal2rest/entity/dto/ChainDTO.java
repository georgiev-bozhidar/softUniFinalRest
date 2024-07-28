package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;

import java.util.Set;

public class ChainDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private ChainOwnerDTO owner;

    private Set<LocationDTO> locations;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public Set<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(Set<LocationDTO> locations) {
        this.locations = locations;
    }

    public @NotBlank Long getId() {
        return id;
    }

    public void setId(@NotBlank Long id) {
        this.id = id;
    }

    public @NotBlank ChainOwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(@NotBlank ChainOwnerDTO owner) {
        this.owner = owner;
    }
}
