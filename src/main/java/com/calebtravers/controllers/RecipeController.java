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
    public String Index(Model model){

        model.addAttribute("title", "Recipe Index");
        model.addAttribute("recipes", recipeDao.findAll());

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
            return "redirect: ";

        }

    }

    @RequestMapping(value="view/{recipeId}", method = RequestMethod.GET)
    public String RecipeDetail(Model model,
                               @PathVariable int recipeId){

        Recipe theRecipe = recipeDao.findOne(recipeId);
        String title = recipeDao.findOne(recipeId).getName();
        model.addAttribute("title", title);
        model.addAttribute("recipe", theRecipe);
        model.addAttribute("ingredients", ingredientDao.findByRecipeId(recipeId));
        model.addAttribute("instructions", instructionDao.findByRecipeId(recipeId));

        return "recipe/detail";
    }

    @RequestMapping(value="edit/{recipeId}", method = RequestMethod.GET)
    public String DisplayEditRecipeForm(Model model,
                             @PathVariable int recipeId){

        EditRecipeForm form = new EditRecipeForm(recipeDao.findOne(recipeId));
        model.addAttribute("form", form);
        model.addAttribute("units", UnitOfMeasure.values());
        model.addAttribute("categories", Category.values());
        model.addAttribute("ingredients", ingredientDao.findByRecipeId(recipeId));
        model.addAttribute("instructions", instructionDao.findByRecipeId(recipeId));
        model.addAttribute("title", "Edit " + recipeDao.findOne(recipeId).getName());

        return "recipe/edit";
    }

    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String ProcessEditRecipeForm(Model model,
                                        @RequestParam int recipeId,
                                        @ModelAttribute @Valid EditRecipeForm form,
                                        Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Edit " + recipeDao.findOne(recipeId).getName());
            model.addAttribute("form", form);
            return "recipe/edit";
        }

        //process valid form data
        Recipe recipe = recipeDao.findOne(recipeId);

        if(form.getIngredient().getName() != ""){
            Ingredient ingredient = form.getIngredient();
            ingredient.setRecipe(recipe);
            ingredientDao.save(ingredient);
        }
        if(form.getIngredient_2().getName() !=""){
            Ingredient ingredient2 = form.getIngredient_2();
            ingredient2.setRecipe(recipe);
            ingredientDao.save(ingredient2);
        }
        if(form.getIngredient_3().getName() != "") {
            Ingredient ingredient3 = form.getIngredient_3();
            ingredient3.setRecipe(recipe);
            ingredientDao.save(ingredient3);
        }
        if(form.getInstruction().getText() != "") {
            Instruction instruction = form.getInstruction();
            instruction.setRecipe(recipe);
            instructionDao.save(instruction);
        }
        if(form.getInstruction_2().getText() != ""){
            Instruction instruction2 = form.getInstruction_2();
            instruction2.setRecipe(recipe);
            instructionDao.save(instruction2);
        }
        if(form.getInstruction_3().getText() != ""){
            Instruction instruction3 = form.getInstruction_3();
            instruction3.setRecipe(recipe);
            instructionDao.save(instruction3);
        }

        recipeDao.save(recipe);

        return "redirect:view/" + recipeId;

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

        recipeDao.delete(id);

        return "redirect:/recipes";

    }


}
