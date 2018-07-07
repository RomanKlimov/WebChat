package ru.itis.chat.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itis.chat.form.UserRegistrationForm;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.UserRepository;

import java.util.Optional;


@Component
public class UserRegistrationFormValidator implements Validator {
    
    @Autowired
    private UserRepository userRepository;
    
    
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserRegistrationForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationForm form = (UserRegistrationForm) target;

        Optional<User> existUser = userRepository.findFirstByLogin(form.getLogin());

        if (existUser.isPresent()){
            errors.reject("bad.login", "Уже зарегистрировано с таким login!");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name", "Пустая имя");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty.lastName", "Пустой фамилия");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Пустой login");
    }
}
