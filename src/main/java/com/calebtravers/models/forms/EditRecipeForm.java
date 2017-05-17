package com.calebtravers.models.forms;

import com.calebtravers.models.Ingredient;
import com.calebtravers.models.Instruction;
import com.calebtravers.models.Recipe;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by adminbackup on 5/9/17.
 */
public class EditRecipeForm {

    @Valid
    private Recipe recipe;

    //child objects
    @Valid
    private Ingredient ingredient;
    @Valid
    private Ingredient ingredient_2;
    @Valid
    private Ingredient ingredient_3;

    @Valid
    private Instruction instruction;
    @Valid
    private Instruction instruction_2;
    @Valid
    private Instruction instruction_3;


    private int recipeId = this.getRecipeId();
    private int ingredientId;
    private int instructionId;

    public EditRecipeForm(){

    }

    public EditRecipeForm(Recipe recipe){

        this.recipe = recipe;

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(int instructionId) {
        this.instructionId = instructionId;
    }

    public Ingredient getIngredient_2() {
        return ingredient_2;
    }

    public void setIngredient_2(Ingredient ingredient_2) {
        this.ingredient_2 = ingredient_2;
    }

    public Ingredient getIngredient_3() {
        return ingredient_3;
    }

    public void setIngredient_3(Ingredient ingredient_3) {
        this.ingredient_3 = ingredient_3;
    }

    public Instruction getInstruction_2() {
        return instruction_2;
    }

    public void setInstruction_2(Instruction instruction_2) {
        this.instruction_2 = instruction_2;
    }

    public Instruction getInstruction_3() {
        return instruction_3;
    }

    public void setInstruction_3(Instruction instruction_3) {
        this.instruction_3 = instruction_3;
    }
}
