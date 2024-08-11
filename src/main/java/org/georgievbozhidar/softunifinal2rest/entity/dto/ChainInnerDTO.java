package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class ChainInnerDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    public @NotBlank Long getId() {
        return id;
    }

    public void setId(@NotBlank Long id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }
}
