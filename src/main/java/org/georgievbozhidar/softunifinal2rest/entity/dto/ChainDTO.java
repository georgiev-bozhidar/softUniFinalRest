package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class ChainDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private UserInnerDTO owner;

    private Set<LocationInnerDTO> locations;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public Set<LocationInnerDTO> getLocations() {
        return locations;
    }

    public void setLocations(Set<LocationInnerDTO> locations) {
        this.locations = locations;
    }

    public @NotBlank Long getId() {
        return id;
    }

    public void setId(@NotBlank Long id) {
        this.id = id;
    }

    public @NotBlank UserInnerDTO getOwner() {
        return owner;
    }

    public void setOwner(@NotBlank UserInnerDTO owner) {
        this.owner = owner;
    }
}
