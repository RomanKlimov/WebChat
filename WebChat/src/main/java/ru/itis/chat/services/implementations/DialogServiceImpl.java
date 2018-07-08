package ru.itis.chat.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.DialogRepository;
import ru.itis.chat.services.interfaces.DialogService;
import ru.itis.chat.services.interfaces.UserService;
import ru.itis.chat.util.CommonUtils;

import java.text.ParseException;
import java.util.List;

@Service
public class DialogServiceImpl implements DialogService {

    @Autowired
    private DialogRepository dialogRepository;

    @Autowired
    private UserService userService;

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
    public Dialog createDialog(Dialog dialog) {
        return dialogRepository.save(dialog);
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

    @Override
    @Transactional
    public void updateDialog(String valueOfMessage, String dialogId, String username) throws ParseException {
        Dialog dialog = dialogRepository.findOneById(Long.valueOf(dialogId));
        List<Message> messages = dialog.getMessages();
        System.out.println(messages.toString());
        User user = userService.getUserByLogin(username).get();
        System.out.println(user.toString());
        Message build = Message.builder().value(valueOfMessage).dialog(dialog).user_mes(user).creationDate(CommonUtils.getCurrentTimeStampForDialogs()).build();
        messages.add(build);
        dialogRepository.save(dialog);
    }
}
