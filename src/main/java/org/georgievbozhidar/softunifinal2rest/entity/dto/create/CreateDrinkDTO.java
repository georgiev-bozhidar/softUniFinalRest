package org.georgievbozhidar.softunifinal2rest.entity.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.georgievbozhidar.softunifinal2rest.entity.enums.DrinkType;

public class CreateDrinkDTO {
    @NotBlank(message = "Drink name must not be blank.")
    private String name;

    @NotNull(message = "Drink type must not be blank.")
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
