package ru.itis.chat.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.UserRepository;
import ru.itis.chat.services.interfaces.DialogService;
import ru.itis.chat.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DialogService dialogService;


    @Override
    public void createUser(User user) {
        if(!userRepository.findFirstByLogin(user.getLogin()).isPresent()) {
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    @Override
    public Dialog getDialog(User user, String loginParam) {
        if (loginParam != null && !loginParam.isEmpty()) {
            List<User> usersDialog = new ArrayList<>();
            usersDialog.add(user);
            usersDialog.add(getUserByLogin(loginParam).get());
            if (!dialogService.checkExistence(usersDialog)){
                Dialog dialog = Dialog.builder().users(usersDialog).build();
                dialogService.createDialog(dialog);
            }

            return dialogService.getDialogByUsers(usersDialog);
        }

        return null;
    }
}
