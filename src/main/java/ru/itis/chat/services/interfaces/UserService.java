package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(User user);
    List<User> getAllExceptCurrentUser(User currentUser);
    Optional<User> getUserByLogin(String login);
    Dialog getDialog(User user, String loginParam);
}
