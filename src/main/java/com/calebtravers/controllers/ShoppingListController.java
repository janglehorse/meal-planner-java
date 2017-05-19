package com.calebtravers.controllers;

import com.calebtravers.models.Recipe;
import com.calebtravers.models.ShoppingList;
import com.calebtravers.models.data.RecipeDao;
import com.calebtravers.models.data.ShoppingListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by adminbackup on 5/19/17.
 */
@Controller
@RequestMapping("lists")
public class ShoppingListController {

    @Autowired
    ShoppingListDao shoppingListDao;
    @Autowired
    RecipeDao recipeDao;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String shoppingListIndex(Model model){

        model.addAttribute("title", "Shopping Lists");
        model.addAttribute("lists", shoppingListDao.findAll());

        return "list/index";

    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String postAddListForm(Model model){

        model.addAttribute("list", new ShoppingList());
        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "Create New Shopping List");

        return "list/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddListForm(Model model,
                                     @RequestParam String userChoice,
                                     @ModelAttribute @Valid ShoppingList list,
                                     Errors errors){

        if(errors.hasErrors()){

            model.addAttribute("list", list);
            model.addAttribute("recipes", recipeDao.findAll());
            model.addAttribute("title", "Create New Shopping List");

            return "list/add";
        }

        shoppingListDao.save(list);

        if(userChoice.equals("continue")){
            return "redirect:/lists/addRecipe/" + list.getId();
        }
        else {
            return "redirect:/lists/view/" + list.getId();
        }
    }

    @RequestMapping(value="view/{listId}", method = RequestMethod.GET)
    public String shoppingListDetail(Model model,
                                     @PathVariable int listId){

        ShoppingList list = shoppingListDao.findOne(listId);

        model.addAttribute("list", list);
        model.addAttribute("title", list.getName());

        return "list/detail";
    }

    @RequestMapping(value="edit/{listId}", method = RequestMethod.GET)
    public String editShoppingListName(Model model,
                                   @PathVariable int listId){

        ShoppingList list = shoppingListDao.findOne(listId);

        model.addAttribute("list", list);
        model.addAttribute("title", "Edit " + list.getName());
        return "list/edit";
    }

    @RequestMapping(value="edit", method = RequestMethod.POST)
    public String processEditShoppingListName(Model model,
                                              @RequestParam int listId,
                                              @ModelAttribute @Valid ShoppingList list,
                                              Errors errors){
        ShoppingList updateList = shoppingListDao.findOne(listId);

        if(errors.hasErrors()){
            model.addAttribute("list", updateList);
            model.addAttribute("title", "Edit " + list.getName());
            return "list/edit";
        }

        updateList.setName(list.getName());
        shoppingListDao.save(updateList);

        return "redirect:view/" + listId;

    }

    @RequestMapping(value="addRecipe/{listId}", method = RequestMethod.GET)
    public String postAddRecipeForm(Model model,
                                    @PathVariable int listId){

        ShoppingList list = shoppingListDao.findOne(listId);

        model.addAttribute("list", list);
        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "Add Recipe to " + list.getName());

        return "list/addRecipe";

    }

    @RequestMapping(value="addRecipe", method= RequestMethod.POST)
    public String processAddRecipeForm(Model model,
                                       @RequestParam int listId,
                                       @RequestParam int recipeId){

        ShoppingList list = shoppingListDao.findOne(listId);
        Recipe recipe = recipeDao.findOne(recipeId);

        list.addRecipe(recipe);
        recipe.getShoppingLists().add(list);

        shoppingListDao.save(list);
        recipeDao.save(recipe);

        return "redirect:view/" + listId;

    }

    @RequestMapping(value="removeRecipe/{listId}", method = RequestMethod.GET)
    public String postDeleteRecipeForm(Model model,
                                       @PathVariable int listId){

        ShoppingList list = shoppingListDao.findOne(listId);
        model.addAttribute("list", list);
        model.addAttribute("title", "Remove Recipe from " + list.getName());

        return "list/removeRecipe";
    }

    @RequestMapping(value="removeRecipe", method= RequestMethod.POST)
    public String processDeleteRecipeForm(Model model,
                                       @RequestParam int listId,
                                       @RequestParam int recipeId){

        ShoppingList list = shoppingListDao.findOne(listId);
        Recipe recipe = recipeDao.findOne(recipeId);

        list.getRecipes().remove(recipe);
        recipe.getShoppingLists().remove(list);

        shoppingListDao.save(list);
        recipeDao.save(recipe);

        return "redirect:view/" + listId;

    }

    @RequestMapping(value="delete/{listId}", method=RequestMethod.GET)
    public String postDeleteListForm(Model model,
                                     @PathVariable int listId){

        ShoppingList list = shoppingListDao.findOne(listId);

        model.addAttribute("list", list);
        model.addAttribute("title", "Are you sure you want to delete " + list.getName());

        return "list/delete";
    }

    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String processDeleteListForm(Model model,
                                        @RequestParam int listId){

        ShoppingList list = shoppingListDao.findOne(listId);

        for(Recipe recipe : list.getRecipes()){
            recipe.getShoppingLists().remove(list);
        }

        shoppingListDao.delete(listId);
        return "redirect:";
    }

}
