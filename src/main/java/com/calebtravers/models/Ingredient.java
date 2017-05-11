package com.calebtravers.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;

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
    private UnitOfMeasure unit = UnitOfMeasure.TBS;

    @NotNull
    @Digits(integer = 2, fraction = 2)
    private BigDecimal quantity = BigDecimal.valueOf(0);

    @NotNull
    private UnitOfMeasure unit_2 = UnitOfMeasure.NA;

    @NotNull
    @Digits(integer = 2, fraction = 2)
    private BigDecimal quantity_2 = BigDecimal.valueOf(0);

    @NotNull
    private Category category = Category.PRODUCE;

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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public UnitOfMeasure getUnit_2() {
        return unit_2;
    }

    public void setUnit_2(UnitOfMeasure unit_2) {
        this.unit_2 = unit_2;
    }

    public BigDecimal getQuantity_2() {
        return quantity_2;
    }

    public void setQuantity_2(BigDecimal quantity_2) {
        this.quantity_2 = quantity_2;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
