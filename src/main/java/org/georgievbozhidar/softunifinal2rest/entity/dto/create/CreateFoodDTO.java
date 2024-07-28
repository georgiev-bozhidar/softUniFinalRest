package org.georgievbozhidar.softunifinal2rest.entity.dto.create;

import jakarta.validation.constraints.NotBlank;

public class CreateFoodDTO {
    @NotBlank(message = "Food name must not be blank.")
    private String name;

    @NotBlank(message = "Description must not be blank.")
    private String description;

    @NotBlank(message = "Ingredients must not be blank.")
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
