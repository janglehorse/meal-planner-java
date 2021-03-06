package com.calebtravers.controllers;

import com.calebtravers.models.Recipe;
import com.calebtravers.models.ShoppingList;
import com.calebtravers.models.data.RecipeDao;
import com.calebtravers.models.data.ShoppingListDao;
import jdk.nashorn.internal.ir.RuntimeNode;
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
    public String shoppingListIndex(Model model,
                                    @RequestParam(value="msg", required = false) String msg){

        model.addAttribute("title", "Shopping Lists");
        model.addAttribute("lists", shoppingListDao.findAll());
        if(msg != null){
            model.addAttribute("message", msg);
        }
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

        String success = list.getName() + " successfully added!";

        if(errors.hasErrors()){

            model.addAttribute("list", list);
            model.addAttribute("recipes", recipeDao.findAll());
            model.addAttribute("title", "Create New Shopping List");

            return "list/add";
        }

        shoppingListDao.save(list);

        if(userChoice.equals("continue")){
            return "redirect:/lists/addRecipe/" + list.getId() + "?msg=" + success;
        }
        else {
            return "redirect:/lists/view/" + list.getId() + "?msg=" + success;
        }
    }

    @RequestMapping(value="view/{listId}", method = RequestMethod.GET)
    public String shoppingListDetail(Model model,
                                     @RequestParam(value="msg", required = false) String msg,
                                     @PathVariable int listId){

        ShoppingList list = shoppingListDao.findOne(listId);

        model.addAttribute("list", list);
        model.addAttribute("title", list.getName());
        if(msg != null){
            model.addAttribute("message", msg);
        }

        return "list/detail";
    }

    @RequestMapping(value="view/sorted/{listId}", method = RequestMethod.GET)
    public String shoppingListSortedDetail(Model model,
                                           @PathVariable int listId){

        ShoppingList list = shoppingListDao.findOne(listId);

        model.addAttribute("list", list);
        model.addAttribute("title", list.getName());

        model.addAttribute("meat", list.getMeat());
        model.addAttribute("produce", list.getProduce());
        model.addAttribute("canned", list.getCanned());
        model.addAttribute("freezer", list.getFreezer());
        model.addAttribute("dairy", list.getDairy());
        model.addAttribute("bakery",list.getBakery());
        model.addAttribute("baking", list.getBaking());
        model.addAttribute("spices", list.getSpices());

        return "list/detail-sorted";

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
        String success = "List name updated!";

        if(errors.hasErrors()){
            model.addAttribute("list", updateList);
            model.addAttribute("title", "Edit " + list.getName());
            return "list/edit";
        }

        updateList.setName(list.getName());
        shoppingListDao.save(updateList);

        return "redirect:view/" + listId + "?msg=" + success;

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
        String success = recipe.getName() + " added to list!";

        list.addRecipe(recipe);
        recipe.getShoppingLists().add(list);

        shoppingListDao.save(list);
        recipeDao.save(recipe);

        return "redirect:view/" + listId + "?msg=" + success;

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
        String success = recipe.getName() + " removed from list";

        list.getRecipes().remove(recipe);
        recipe.getShoppingLists().remove(list);

        shoppingListDao.save(list);
        recipeDao.save(recipe);

        return "redirect:view/" + listId + "?msg=" + success;

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
        String success = list.getName() + " successfully deleted";

        for(Recipe recipe : list.getRecipes()){
            recipe.getShoppingLists().remove(list);
        }

        shoppingListDao.delete(listId);
        return "redirect:" + "?msg=" + success;
    }

}
