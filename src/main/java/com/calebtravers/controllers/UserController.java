package com.calebtravers.controllers;

import com.calebtravers.models.User;
import com.calebtravers.service.SecurityService;
import com.calebtravers.service.UserService;
import com.calebtravers.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by adminbackup on 5/30/17.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register(Model model){

        model.addAttribute("user", new User());
        model.addAttribute("title", "Signup");

        return "user/register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@ModelAttribute @Valid User user,
                               Errors errors,
                               Model model){

        userValidator.validate(user, errors);

        if (errors.hasErrors()){
            return "user/register";
        }

        userService.save(user);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/welcome";

    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model,
                        String error,
                        String logout){
        if (error != null)
            model.addAttribute("error", "Invalid username and password.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "user/login";
    }

    @RequestMapping(value="/welcome", method=RequestMethod.GET)
    public String welcome(Model model) {
        return "user/welcome";
    }

}
