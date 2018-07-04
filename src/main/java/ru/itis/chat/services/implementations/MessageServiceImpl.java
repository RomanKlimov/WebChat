package ru.itis.chat.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.Message;
import ru.itis.chat.repositories.MessageRepository;
import ru.itis.chat.services.interfaces.MessageService;

import java.util.Set;

public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Set<Message> getMessagesForDialog(Dialog dialog) {
        return messageRepository.findAllByDialog(dialog);
    }
}
