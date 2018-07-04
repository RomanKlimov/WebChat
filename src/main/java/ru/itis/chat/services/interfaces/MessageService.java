package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.User;

import java.util.List;
import java.util.Set;

public interface MessageService {
    Set<Message> getMessagesForDialog(Dialog dialog);
}
