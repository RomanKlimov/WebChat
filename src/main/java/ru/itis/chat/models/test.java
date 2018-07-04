package ru.itis.chat.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.repositories.DialogRepository;
import ru.itis.chat.repositories.MessageRepository;
import ru.itis.chat.repositories.UserRepository;

import java.util.*;

@Service
public class test {

    public test(){
        /*create();
        read();*/
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private DialogRepository dialogRepository;

    public void create(){
        User user = User.builder().login("111").hashPassword("222").build();
        User user2 = User.builder().login("weqe2").hashPassword("2dwq").build();
        userRepository.save(user);
        userRepository.save(user2);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        Message message = Message.builder().value("message").build();
        messageRepository.save(message);
        Set<Message> messages = new HashSet<>();
        messages.add(message);
        Dialog build = Dialog.builder().users(users).messages(messages).build();
        dialogRepository.save(build);
    }

    public void read(){
        User one = userRepository.findOne(2L);
        List<Dialog> dialogs = one.getDialogs();
        System.out.println(Arrays.asList(dialogs));
    }
}
