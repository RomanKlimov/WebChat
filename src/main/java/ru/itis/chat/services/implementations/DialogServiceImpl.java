package ru.itis.chat.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.DialogRepository;
import ru.itis.chat.services.interfaces.DialogService;

import java.util.List;
import java.util.Optional;

@Service
public class DialogServiceImpl implements DialogService {

    @Autowired
    private DialogRepository dialogRepository;

    @Override
    public boolean checkExistence(List<User> users) {
        Dialog dialog = getDialogByUsers(users);
        if(dialog != null) {
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

    @Override
    public Dialog getDialogByUsers(List<User> users) {
        List<Dialog> dialogs1 = users.get(0).getDialogs();
        List<Dialog> dialogs2 = users.get(1).getDialogs();
        for(Dialog dialog : dialogs1){
            if(dialogs2.contains(dialog)){
                return dialog;
            }
        }
        return null;
    }
}
