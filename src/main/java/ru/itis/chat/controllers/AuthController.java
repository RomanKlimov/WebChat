package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.chat.models.User;
import ru.itis.chat.security.Role.Role;
import ru.itis.chat.services.interfaces.AuthService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String root(HttpServletRequest request, Authentication authentication){
         if (authentication != null){
             User user = authService.getUserByAuthentication(authentication);
             request.getSession().setAttribute("user", user);
             if (user.getRole().equals(Role.USER)){
                 return "redirect:/home";
             }
         }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication, HttpServletRequest request){
        if (authentication != null){
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }
}
