package ru.itis.chat.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.DialogRepository;
import ru.itis.chat.services.interfaces.DialogService;

import java.util.List;

public class DialogServiceImpl implements DialogService {

    @Autowired
    private DialogRepository dialogRepository;

    @Override
    public boolean checkExistence(List<User> users) {
        if(dialogRepository.findDialogByUsersContaining(users).isPresent()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void createDialog(Dialog dialog) {
        dialogRepository.save(dialog);
    }
}
