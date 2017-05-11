package com.calebtravers.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by adminbackup on 5/4/17.
 */

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=55)
    private String name;

    @NotNull
    @Size(min=3, max=155)
    private String description;

    @OneToMany
    @JoinColumn(name="ingredient_id")
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="instruction_id")
    private List<Instruction> instructions = new ArrayList<>();

    public Recipe(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

}
