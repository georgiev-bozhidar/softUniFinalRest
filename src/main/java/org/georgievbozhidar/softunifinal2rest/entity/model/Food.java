package org.georgievbozhidar.softunifinal2rest.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "foods")
public class Food extends Consumable {
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String ingredients;

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
