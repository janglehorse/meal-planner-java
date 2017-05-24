package com.calebtravers.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by adminbackup on 5/19/17.
 */
@Entity
public class ShoppingList {

    @Id
    @GeneratedValue
    int id;

    @NotNull
    @Size(min = 3, max = 55)
    private String name;

    @CreationTimestamp
    java.sql.Timestamp createdDate;

    @ManyToMany
    private List<Recipe> recipes = new ArrayList<>();

    public ShoppingList(){

    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe){
        this.getRecipes().add(recipe);
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public ArrayList<Ingredient> getMeat(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.MEAT){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

    public ArrayList<Ingredient> getProduce(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.PRODUCE){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

    public ArrayList<Ingredient> getDairy(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.DAIRY){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

    public ArrayList<Ingredient> getFreezer(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.FREEZER){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

    public ArrayList<Ingredient> getBakery(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.BAKERY){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

    public ArrayList<Ingredient> getBaking(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.BAKING){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

    public ArrayList<Ingredient> getCanned(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.CANNED){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

    public ArrayList<Ingredient> getSpices(){

        ArrayList<Ingredient> results = new ArrayList<>();

        for(Recipe recipe : this.getRecipes()){
            for(Ingredient ingredient : recipe.getIngredients()){
                if(ingredient.getCategory() == Category.SPICES){
                    results.add(ingredient);
                }
            }
        }
        return results;
    }

}
