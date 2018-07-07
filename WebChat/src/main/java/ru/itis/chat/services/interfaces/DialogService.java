package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DialogService {
    boolean checkExistence(List<User> users);
    void createDialog(Dialog dialog);
    Dialog getDialogByUsers(List<User> users);
    void updateDialog(String valueOfMessage, String dialogId, String username);
}
