package com.peter.controller;

import com.peter.configuration.WebSecurityConfig;

import com.peter.model.User;
import com.peter.Service.SecurityService;
import com.peter.Service.UserService;
import com.peter.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.peter.controller.RestResponseEntityExceptionHandler;
@Controller


public class UserController {
	
	 public static final String ERROR_MSG = "An error occurred!";
    
	 private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    @InitBinder
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
      userValidator.validate(userForm, bindingResult);
      
          
      
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
    	    	   	
        return "welcome";
    }
    
  
    }
      
      
      
    
    


    
    
    
    
    
    
