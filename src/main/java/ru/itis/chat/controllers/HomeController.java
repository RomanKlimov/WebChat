package ru.itis.chat.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.DialogRepository;
import ru.itis.chat.services.implementations.DialogServiceImpl;
import ru.itis.chat.services.implementations.MessageServiceImpl;
import ru.itis.chat.services.implementations.UserServiceImpl;
import ru.itis.chat.services.interfaces.AuthService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    DialogServiceImpl dialogService;

    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    DialogRepository dialogRepository;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/home")
    public String home(@ModelAttribute("model")ModelMap modelMap,
                       @RequestParam(value = "login", required = false) String loginParam,
                       Authentication authentication) {
        List<User> userList = userService.getAllUsers();
        modelMap.addAttribute("userList", userList);
        User user = authService.getUserByAuthentication(authentication);
        Dialog dialog = userService.getDialog(user, loginParam);
        if (dialog != null){
            modelMap.addAttribute("dialog",dialog);
            return "home";
        }

        modelMap.addAttribute("dialog",dialog);

        return "home";
    }

    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam(value = "value") String value, @RequestParam(value = "dialogId") Long dialogId, Authentication authentication) {
        Dialog dialog = dialogRepository.findOneById(dialogId);
        Set<Message> messages = dialog.getMessages();
        User user = authService.getUserByAuthentication(authentication);
        Message build = Message.builder().value(value).dialog(dialog).user_mes(user).build();
        messages.add(build);
        dialog.setMessages(messages);
        dialogRepository.save(dialog);
        return "redirect:/home";
    }


}
