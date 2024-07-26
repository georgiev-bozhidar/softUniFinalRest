package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;
import org.georgievbozhidar.softunifinal2rest.entity.enums.DrinkType;

public class DrinkDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    private DrinkType drinkType;

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

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }
}
