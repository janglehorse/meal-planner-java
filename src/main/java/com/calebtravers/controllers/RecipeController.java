package com.calebtravers.controllers;

import com.calebtravers.models.*;
import com.calebtravers.models.data.IngredientDao;
import com.calebtravers.models.data.InstructionDao;
import com.calebtravers.models.data.RecipeDao;
import com.calebtravers.models.forms.EditRecipeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adminbackup on 5/3/17.
 */

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private IngredientDao ingredientDao;
    @Autowired
    private InstructionDao instructionDao;

    @RequestMapping(value="")
    public String Index(Model model,
                        @RequestParam(value="msg", required = false) String msg){

        model.addAttribute("title", "Recipe Index");
        model.addAttribute("recipes", recipeDao.findAll());
        if(msg != null){
            model.addAttribute("message", msg);
        }

        return "recipe/index";

    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String DisplayAddForm(Model model){

        model.addAttribute("title", "Add Recipe");
        model.addAttribute(new Recipe());
        model.addAttribute(new Instruction());
        return "recipe/add";

    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String ProcessAddForm (@ModelAttribute @Valid Recipe newRecipe,
                                  Errors errors,
                                  Model model){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Recipe");
            return "recipe/add";
        }
        else{
            recipeDao.save(newRecipe);
            String success = newRecipe.getName() + " added!";
            return "redirect:" + "?msg=" + success;

        }

    }

    @RequestMapping(value="view/{recipeId}", method = RequestMethod.GET)
    public String RecipeDetail(Model model,
                               @RequestParam(value="msg", required = false) String msg,
                               @PathVariable int recipeId){

        Recipe theRecipe = recipeDao.findOne(recipeId);
        String title = recipeDao.findOne(recipeId).getName();

        model.addAttribute("title", title);
        model.addAttribute("recipe", theRecipe);
        model.addAttribute("ingredients", ingredientDao.findByRecipeId(recipeId));
        model.addAttribute("instructions", instructionDao.findByRecipeId(recipeId));
        model.addAttribute("message", msg);

        return "recipe/detail";
    }

    @RequestMapping(value="edit/{recipeId}", method = RequestMethod.GET)
    public String DisplayEditRecipeForm(Model model,
                             @PathVariable int recipeId){

        Recipe recipe = recipeDao.findOne(recipeId);

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredientDao.findByRecipeId(recipeId));
        model.addAttribute("instructions", instructionDao.findByRecipeId(recipeId));
        model.addAttribute("title", "Edit " + recipe.getName());

        return "recipe/edit";
    }

    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String ProcessEditRecipeForm(Model model,
                                        @RequestParam int recipeId,
                                        @ModelAttribute @Valid Recipe recipe,
                                        Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Edit " + recipeDao.findOne(recipeId).getName());
            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredients", ingredientDao.findByRecipeId(recipeId));
            model.addAttribute("instructions", instructionDao.findByRecipeId(recipeId));
            return "recipe/edit";
        }

        //process valid form data
        Recipe updateRecipe = recipeDao.findOne(recipeId);
        String success = "Update Successful!";

        updateRecipe.setName(recipe.getName());
        updateRecipe.setDescription(recipe.getDescription());
        recipeDao.save(updateRecipe);

        return "redirect:view/" + updateRecipe.getId() + "?msg=" + success;

    }

    @RequestMapping(value = "delete/{recipeId}", method = RequestMethod.GET)
    public String DisplayDeleteRecipeForm(Model model,
                                          @PathVariable int recipeId){

        model.addAttribute("title", "Are you sure you want to delete" + " " +
                            recipeDao.findOne(recipeId).getName() + "?");
        model.addAttribute("ingredients", ingredientDao.findByRecipeId(recipeId));
        model.addAttribute("id", recipeId);

        return "recipe/delete";

    }

    @RequestMapping(value= "delete", method = RequestMethod.POST)
    public String ProcessDeleteForm(@RequestParam int id){

        List<Ingredient> deleteIngredients = ingredientDao.findByRecipeId(id);
        List<Instruction> deleteInstructions = instructionDao.findByRecipeId(id);

        ingredientDao.delete(deleteIngredients);
        instructionDao.delete(deleteInstructions);
        recipeDao.delete(id);

        return "redirect:/recipes";

    }

    @RequestMapping(value="search", method = RequestMethod.GET)
    public String recipeSearchResults(Model model,
                                      @RequestParam String searchTerm){

        if(searchTerm.matches("[a-zA-Z0-9]+")) {

            ArrayList<Recipe> results = new ArrayList<>();

            for(Recipe recipe : recipeDao.findAll()){

                if(recipe.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                    results.add(recipe);
                }
            }

            if (results.size() > 0) {
                model.addAttribute("title", "Search results for " + "'" +  searchTerm + "'");
                model.addAttribute("results", results);
            } else {
                model.addAttribute("title", "Search results for " + "'" +  searchTerm + "'");
            }
        }
        else{
            model.addAttribute("title", "Please enter numbers or letters for search");
      }

      return "recipe/search";

    }

}
