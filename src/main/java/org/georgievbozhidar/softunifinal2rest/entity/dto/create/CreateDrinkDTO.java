package org.georgievbozhidar.softunifinal2rest.entity.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.georgievbozhidar.softunifinal2rest.entity.enums.DrinkType;

public class CreateDrinkDTO {
    @NotBlank
    private String name;
    @NotNull
    private DrinkType drinkType;

    public @NotNull DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(@NotNull DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }
}
