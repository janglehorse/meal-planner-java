package com.calebtravers.controllers;

import com.calebtravers.models.Category;
import com.calebtravers.models.Ingredient;
import com.calebtravers.models.Recipe;
import com.calebtravers.models.UnitOfMeasure;
import com.calebtravers.models.data.IngredientDao;
import com.calebtravers.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by adminbackup on 5/17/17.
 */
@Controller
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    RecipeDao recipeDao;
    @Autowired
    private IngredientDao ingredientDao;

    @RequestMapping(value = "add/{recipeId}", method = RequestMethod.GET)
    public String addIngredient(@PathVariable int recipeId,
                                Model model){

        model.addAttribute("title", "Add ingredient to " + recipeDao.findOne(recipeId).getName());
        model.addAttribute("recipeId", recipeId);
        model.addAttribute(new Ingredient());
        model.addAttribute("categories", Category.values());
        model.addAttribute("units", UnitOfMeasure.values());

        return "ingredient/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processNewIngredient(Model model,
                                       @RequestParam String userChoice,
                                       @RequestParam int recipeId,
                                       @ModelAttribute @Valid Ingredient ingredient,
                                       Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add ingredient to " + recipeDao.findOne(recipeId).getName());
            model.addAttribute("recipeId", recipeId);
            model.addAttribute(ingredient);
            model.addAttribute("categories", Category.values());
            model.addAttribute("units", UnitOfMeasure.values());
            return "ingredient/add";
        }

        Recipe recipe = recipeDao.findOne(recipeId);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        ingredientDao.save(ingredient);
        recipeDao.save(recipe);


        if(userChoice.equals("continue")){
            return "redirect:/ingredients/add/" + recipeId;
        }
        else {
            return "redirect:/recipes/view/" + recipeId;
        }

    }

    @RequestMapping(value = "edit/{ingredientId}/{recipeId}", method = RequestMethod.GET)
    public String postEditIngredientForm(Model model,
                                         @PathVariable int recipeId,
                                         @PathVariable int ingredientId){

        Ingredient ingredient = ingredientDao.findOne(ingredientId);

        model.addAttribute("title", "Edit " + ingredient.getName());
        model.addAttribute("recipe", recipeDao.findOne(recipeId));
        model.addAttribute(ingredient);
        model.addAttribute("units", UnitOfMeasure.values());
        model.addAttribute("categories", Category.values());

        return "ingredient/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditIngredientForm(Model model,
                                            @RequestParam int recipeId,
                                            @ModelAttribute @Valid Ingredient ingredient,
                                            Errors errors){

        if(errors.hasErrors()){

            model.addAttribute("title", "Edit " + ingredient.getName());
            model.addAttribute("recipe", recipeDao.findOne(recipeId));
            model.addAttribute(ingredient);
            model.addAttribute("units", UnitOfMeasure.values());
            model.addAttribute("categories", Category.values());
        }

        Recipe theRecipe = recipeDao.findOne(recipeId);
        ingredient.setRecipe(theRecipe);
        theRecipe.addIngredient(ingredient);
        ingredientDao.save(ingredient);
        recipeDao.save(theRecipe);

        return "redirect:/recipes/view/" + recipeId;

    }

    @RequestMapping(value="delete/{ingredientId}/{recipeId}", method=RequestMethod.GET)
    public String postDeleteIngredientForm(Model model,
                                           @PathVariable int ingredientId,
                                           @PathVariable int recipeId){

        Ingredient ingredient = ingredientDao.findOne(ingredientId);
        Recipe recipe = recipeDao.findOne(recipeId);
        model.addAttribute("title", "Are you sure you want to delete " + ingredient.getName() +
                            " from" + recipe.getName() + "?");
        model.addAttribute(ingredient);
        model.addAttribute("recipeId", recipeId);

        return "ingredient/delete";
    }


    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String processDeleteIngredientForm(@RequestParam int ingredientId,
                                              @RequestParam int recipeId){

        ingredientDao.delete(ingredientId);

        return "redirect:/recipes/view/" + recipeId;

        //TODO:
        //STORE DELETED INGREDIENT NAME IN VAR:
        //String confirmDelete = ingredientDao.findOne(ingredientId).getName();
        //SEND VAR IN QUERY STRING TO RECIPE CONTROLLER TO RENDER CONFIRMATION MESSAGE
        //+ "?msg=" + confirmDelete;

    }



}
