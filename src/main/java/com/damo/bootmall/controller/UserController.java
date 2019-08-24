package com.damo.bootmall.controller;

import com.damo.bootmall.UserValidator;
import com.damo.bootmall.bean.User;
import com.damo.bootmall.service.SecurityService;
import com.damo.bootmall.service.UserService;
import com.mysql.jdbc.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,Model model){

        userValidator.validate(user,bindingResult);

        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                model.addAttribute(fieldError.getField(),true);
            });
            return "registration";
        }

        userService.save(user);
        securityService.autoLogin(user.getUsername(),user.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model,String error,String logout){
        if(error != null){
            model.addAttribute("error","Your username and password is invalid.");
        }

        if(logout != null){
            model.addAttribute("message","You have been logged out.");
        }

        return "login";
    }

    @GetMapping({"/","/welcome"})
    public String welcome(Principal principal,Model model){
        model.addAttribute("name",principal.getName());
        return "welcome";
    }
}
