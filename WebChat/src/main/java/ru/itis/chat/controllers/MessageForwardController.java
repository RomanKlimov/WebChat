package ru.itis.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.dto.ChatMessage;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.AuthService;
import ru.itis.chat.services.interfaces.DialogService;
import ru.itis.chat.services.interfaces.UserService;
import ru.itis.chat.util.CommonUtils;


import java.security.Principal;
import java.text.ParseException;

/**
 * Implements user-to-user sock js based message sending
 * controller methods.
 *
 */
@Controller
public class MessageForwardController  {

    @Autowired
    private SimpMessagingTemplate webSocket;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private DialogService dialogService;

    @RequestMapping("/home")
    public String getChatBot(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = authService.getUserByAuthentication(auth);
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            modelMap.addAttribute("username", user.getLogin());
            modelMap.addAttribute("onlineUsers", userService.getAllExceptCurrentUser(user));
            return "home";
        }
        return "login";
    }

    @MessageMapping("/chat/{dialogId}")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage, Principal principal, @DestinationVariable("dialogId") String dialogId) throws ParseException {

        dialogService.updateDialog(chatMessage.getText(), dialogId, principal.getName());
        chatMessage.setFrom(principal.getName());
        chatMessage.setRecipient(dialogId);
        String time = CommonUtils.getCurrentTimeStamp();
        chatMessage.setTime(time);


        System.out.println(dialogId);
        System.out.println(principal.getName());
        webSocket.convertAndSend("/user1/" + dialogId, chatMessage);
    }

    @GetMapping(value = "/dialog")
    public String getDialog(@RequestParam(value = "friend") String friend, Authentication authentication, ModelMap modelMap) {
        User user1 = authService.getUserByAuthentication(authentication);
        User user2 = userService.getUserByLogin(friend).get();
        Dialog dialog = userService.getDialog(user1, friend);
        modelMap.addAttribute("username", user1);
        modelMap.addAttribute("friend", user2);
        modelMap.addAttribute("dialog", dialog);
        return "dialog";
    }

}
