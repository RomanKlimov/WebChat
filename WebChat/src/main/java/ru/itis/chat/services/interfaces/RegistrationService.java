package ru.itis.chat.services.interfaces;


import ru.itis.chat.dto.UserRegistrationForm;


public interface RegistrationService {
    void createUserAccount(UserRegistrationForm userRegistrationForm) throws Exception;
}
