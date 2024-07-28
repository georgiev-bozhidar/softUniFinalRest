package org.georgievbozhidar.softunifinal2rest.entity.dto.update;

import jakarta.validation.constraints.NotBlank;

public class UpdateFoodDTO {
    private String name;

    private String description;

    private String ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
