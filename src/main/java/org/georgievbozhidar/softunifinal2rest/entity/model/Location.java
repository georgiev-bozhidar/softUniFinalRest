package org.georgievbozhidar.softunifinal2rest.entity.model;

import jakarta.persistence.*;
import org.georgievbozhidar.softunifinal2rest.entity.enums.LocationType;

import java.util.Set;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String address;

    @ManyToOne
    private Chain ownedBy;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Food> foods;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Drink> drinks;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Chain getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Chain ownedBy) {
        this.ownedBy = ownedBy;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }

    public void addFood(Food food){
        this.getFoods().add(food);
    }

    public void addDrink(Drink drink){
        this.getDrinks().add(drink);
    }

    public void removeFood(Food food){
        this.getFoods().remove(food);
    }

    public void removeDrink(Drink drink){
        this.getDrinks().remove(drink);
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }
}
