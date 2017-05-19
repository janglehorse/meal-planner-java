package com.calebtravers.controllers;

import com.calebtravers.models.*;
import com.calebtravers.models.data.InstructionDao;
import com.calebtravers.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by adminbackup on 5/18/17.
 */
@Controller
@RequestMapping("instructions")
public class InstructionController {

    @Autowired
    InstructionDao instructionDao;
    @Autowired
    RecipeDao recipeDao;

    @RequestMapping(value = "add/{recipeId}", method = RequestMethod.GET)
    public String addInstruction(@PathVariable int recipeId,
                                Model model){

        model.addAttribute("title", "Add instruction to " + recipeDao.findOne(recipeId).getName());
        model.addAttribute("recipeId", recipeId);
        model.addAttribute(new Instruction());

        return "instruction/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processNewInstruction(Model model,
                                       @RequestParam String userChoice,
                                       @RequestParam int recipeId,
                                       @ModelAttribute @Valid Instruction instruction,
                                       Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add instruction to " + recipeDao.findOne(recipeId).getName());
            model.addAttribute("recipeId", recipeId);
            model.addAttribute(instruction);
            return "instruction/add";
        }

        Recipe recipe = recipeDao.findOne(recipeId);
        recipe.addInstruction(instruction);
        instruction.setRecipe(recipe);
        instructionDao.save(instruction);
        recipeDao.save(recipe);


        if(userChoice.equals("continue")){
            return "redirect:/instructions/add/" + recipeId;
        }
        else {
            return "redirect:/recipes/view/" + recipeId;
        }

    }

    @RequestMapping(value = "edit/{instructionId}/{recipeId}", method = RequestMethod.GET)
    public String postEditinstructionForm(Model model,
                                         @PathVariable int recipeId,
                                         @PathVariable int instructionId){

        Instruction instruction = instructionDao.findOne(instructionId);
        Recipe recipe = recipeDao.findOne(recipeId);

        model.addAttribute("title", "Edit instruction for " + recipe.getName());
        model.addAttribute(recipe);
        model.addAttribute(instruction);

        return "instruction/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditInstructionForm(Model model,
                                            @RequestParam int recipeId,
                                             @RequestParam int instructionId,
                                            @ModelAttribute @Valid Instruction instruction,
                                            Errors errors){

        Recipe recipe = recipeDao.findOne(recipeId);
        Instruction updateInstruction = instructionDao.findOne(instructionId);

        if(errors.hasErrors()){

            model.addAttribute("title", "Edit instruction for " + recipe.getName());
            model.addAttribute(recipe);
            model.addAttribute("instruction", updateInstruction);
        }

        updateInstruction.setNumber(instruction.getNumber());
        updateInstruction.setText(instruction.getText());
        instructionDao.save(updateInstruction);

        return "redirect:/recipes/view/" + recipeId;

    }

    @RequestMapping(value="delete/{instructionId}/{recipeId}", method=RequestMethod.GET)
    public String postDeleteInstructionForm(Model model,
                                           @PathVariable int instructionId,
                                           @PathVariable int recipeId){

        Instruction instruction = instructionDao.findOne(instructionId);
        Recipe recipe = recipeDao.findOne(recipeId);
        model.addAttribute("title", "Delete this instruction from " + recipe.getName() + "?");
        model.addAttribute(instruction);
        model.addAttribute("recipeId", recipeId);

        return "instruction/delete";
    }


    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String processDeleteInstructionForm(@RequestParam int instructionId,
                                              @RequestParam int recipeId){

        Instruction deleteInstruction = instructionDao.findOne(instructionId);

        instructionDao.delete(instructionId);
        recipeDao.findOne(recipeId).getInstructions().remove(deleteInstruction);

        return "redirect:/recipes/view/" + recipeId;

        //TODO:
        //STORE DELETED INGREDIENT NAME IN VAR:
        //String confirmDelete = instructionDao.findOne(instructionId).getName();
        //SEND VAR IN QUERY STRING TO RECIPE CONTROLLER TO RENDER CONFIRMATION MESSAGE
        //+ "?msg=" + confirmDelete;

    }

}
