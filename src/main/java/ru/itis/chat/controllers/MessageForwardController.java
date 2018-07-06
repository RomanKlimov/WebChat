package ru.itis.chat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.chat.dto.ChatMessage;
import ru.itis.chat.dto.OutputMessage;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.UserRepository;
import ru.itis.chat.services.interfaces.AuthService;
import ru.itis.chat.services.interfaces.UserService;
import ru.itis.chat.util.ActiveSessionManager;
import ru.itis.chat.util.CommonUtils;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Implements user-to-user sock js based message sending
 * controller methods.
 *
 * @author Yasitha Thilakaratne
 */
@Controller
@RequestMapping("msg-forward")
public class MessageForwardController implements ActiveSessionManager.ActiveUserChangeListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageForwardController.class);

    @Autowired
    private SimpMessagingTemplate webSocket;

//    @Autowired
//    private ActiveSessionManager activeSessionManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @PostConstruct
//    private void init() {
//        activeSessionManager.registerListener(this);
//    }
//
//    @PreDestroy
//    private void destroy() {
//        activeSessionManager.removeListener(this);
//    }

    @RequestMapping("/chatbot")
    public String getChatBot(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = authService.getUserByAuthentication(auth);
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            modelMap.addAttribute("username", user.getLogin());
            modelMap.addAttribute("onlineUsers", userService.getAllExceptCurrentUser(user));
            return "sockJsEndToEndChat";
        }
        return "login";
    }

    @MessageMapping("/chat")
    public void send(Message<ChatMessage> message, @Payload ChatMessage chatMessage) throws Exception {
        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        if (principal == null) {
            LOGGER.error("Principal is null");
            return;
        }
        String authenticatedSender = principal.getName();
        String time = CommonUtils.getCurrentTimeStamp();

        if (!authenticatedSender.equals(chatMessage.getRecipient())) {
            webSocket.convertAndSendToUser(authenticatedSender, "/queue/messages",
                    new OutputMessage(chatMessage.getFrom(), chatMessage.getText(), time, true));
        }

        webSocket.convertAndSendToUser(chatMessage.getRecipient(), "/queue/messages",
                new OutputMessage(chatMessage.getFrom(), chatMessage.getText(), time, false));

    }

    /**
     * This method will get called when Observable's internal state
     * is changed.
     */
    public void notifyActiveUserChange() {
        List<User> activeUsers = userRepository.findAll();
        webSocket.convertAndSend("/topic/active", activeUsers);
    }
}
