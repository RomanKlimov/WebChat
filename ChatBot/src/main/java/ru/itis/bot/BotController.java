package ru.itis.bot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.itis.bot.util.CommonUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class BotController {

    @Autowired
    private SimpMessagingTemplate webSocket;

    Map<String, List<Integer>> usersArticlels = new HashMap<>();

    @MessageMapping("/chat/{dialogId}")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage, @DestinationVariable("dialogId") String dialogId) {

        List<Integer> listOfArticles = usersArticlels.get(chatMessage.getFrom());
        if(listOfArticles == null) {
            listOfArticles = new ArrayList<>();
        }
        int randomNum = ThreadLocalRandom.current().nextInt(1, 416500);
        while (!listOfArticles.contains(randomNum)){
            randomNum = ThreadLocalRandom.current().nextInt(1, 416500);
            listOfArticles.add(randomNum);
        }

        usersArticlels.put(chatMessage.getFrom(), listOfArticles);
        chatMessage.setText("https://habr.com/post/" + randomNum + "/");
        chatMessage.setRecipient(dialogId);
        String time = CommonUtils.getCurrentTimeStamp();
        chatMessage.setTime(time);

        webSocket.convertAndSend("/user1/" + dialogId, chatMessage);

    }
}
