package com.calebtravers.controllers;

import com.calebtravers.models.Category;
import com.calebtravers.models.Ingredient;
import com.calebtravers.models.Recipe;
import com.calebtravers.models.UnitOfMeasure;
import com.calebtravers.models.data.IngredientDao;
import com.calebtravers.models.data.InstructionDao;
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
    @Autowired
    private InstructionDao instructionDao;

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

}
