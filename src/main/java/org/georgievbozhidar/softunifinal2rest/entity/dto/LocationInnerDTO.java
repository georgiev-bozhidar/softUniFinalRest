package org.georgievbozhidar.softunifinal2rest.entity.dto;

import jakarta.validation.constraints.NotBlank;
import org.georgievbozhidar.softunifinal2rest.entity.enums.LocationType;

import java.util.Set;

public class LocationInnerDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String address;

    private LocationType locationType;

    private Set<FoodDTO> foods;

    private Set<DrinkDTO> drinks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank String address) {
        this.address = address;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Set<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodDTO> foods) {
        this.foods = foods;
    }

    public Set<DrinkDTO> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<DrinkDTO> drinks) {
        this.drinks = drinks;
    }
}
