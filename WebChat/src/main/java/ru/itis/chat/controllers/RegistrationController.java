package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.chat.dto.UserRegistrationForm;
import ru.itis.chat.services.interfaces.RegistrationService;
import ru.itis.chat.validator.UserRegistrationFormValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;


    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder){
        binder.addValidators(userRegistrationFormValidator);
    }

    @PostMapping("/signUp")
    public String registerUserAccount(@ModelAttribute("userForm") @Valid UserRegistrationForm userRegistrationForm,
                                      BindingResult result,  RedirectAttributes attributes) throws Exception {
        if (result.hasErrors()){
            System.out.println(result.getAllErrors().get(0).getDefaultMessage());
            attributes.addFlashAttribute("error" , result.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }
        registrationService.createUserAccount(userRegistrationForm);
        return "redirect:/home";
    }

    @GetMapping("/signUp")
    public String signUp(Model model){
        if (!model.containsAttribute("userRegistrationForm")){
            model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        }
        return "signUp";
    }

}
