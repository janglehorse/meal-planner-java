package com.calebtravers.models;

import com.calebtravers.models.Recipe;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by adminbackup on 5/8/17.
 */

@Entity
public class Instruction {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Min(value=1)
    @Max(value=99)
    private Integer number;

    @NotNull
    @Size(min=3, max=255)
    private String text;

    @ManyToOne
    private Recipe recipe;

    public Instruction() {
    }

    public int getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
