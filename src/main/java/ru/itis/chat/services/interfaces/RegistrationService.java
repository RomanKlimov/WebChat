package ru.itis.chat.services.interfaces;


import ru.itis.chat.exceptions.EmailExistsException;
import ru.itis.chat.form.UserRegistrationForm;


public interface RegistrationService {
    void createUserAccount(UserRegistrationForm userRegistrationForm) throws EmailExistsException;
}
