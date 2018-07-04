package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
}
