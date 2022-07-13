package com.cesar.projeto.sr2.aurum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cesar.projeto.sr2.aurum.model.UserModel;
import com.cesar.projeto.sr2.aurum.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        UserModel registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(), userModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login"; //if-else em uma linha
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if(authenticated != null){
            model.addAttribute("userLogin", authenticated.getLogin());
            return "main_page";
        }else{
            return "error_page";
        }
    }
}
