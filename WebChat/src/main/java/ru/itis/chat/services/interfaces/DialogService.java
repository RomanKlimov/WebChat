package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.User;

import java.text.ParseException;
import java.util.List;

public interface DialogService {
    boolean checkExistence(List<User> users);
    Dialog createDialog(Dialog dialog);
    Dialog getDialogByUsers(List<User> users);
    void updateDialog(String valueOfMessage, String dialogId, String username) throws ParseException;
}
