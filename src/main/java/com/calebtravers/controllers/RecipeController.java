package com.calebtravers.controllers;

import com.calebtravers.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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




}
