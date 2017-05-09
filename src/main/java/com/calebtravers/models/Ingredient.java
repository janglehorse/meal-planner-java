package com.calebtravers.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by adminbackup on 5/4/17.
 */

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=55)
    private String name;

    @NotNull
    private UnitOfMeasure unit;

    @NotNull
    @Size(max=99)
    private Float quantity;

    @NotNull
    private UnitOfMeasure unit_2;

    @NotNull
    @Size(max=99)
    private Float quantity_2;

    @NotNull
    private Category category;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public UnitOfMeasure getUnit_2() {
        return unit_2;
    }

    public void setUnit_2(UnitOfMeasure unit_2) {
        this.unit_2 = unit_2;
    }

    public Float getQuantity_2() {
        return quantity_2;
    }

    public void setQuantity_2(Float quantity_2) {
        this.quantity_2 = quantity_2;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
