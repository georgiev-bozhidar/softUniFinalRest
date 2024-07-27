package org.georgievbozhidar.softunifinal2rest.entity.dto.create;

import jakarta.validation.constraints.NotBlank;

public class CreateFoodDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String ingredients;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    public @NotBlank String getIngredients() {
        return ingredients;
    }

    public void setIngredients(@NotBlank String ingredients) {
        this.ingredients = ingredients;
    }
}
