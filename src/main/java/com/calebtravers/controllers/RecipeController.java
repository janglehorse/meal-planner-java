package com.calebtravers.controllers;

import com.calebtravers.models.Recipe;
import com.calebtravers.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by adminbackup on 5/3/17.
 */

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;

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


}
