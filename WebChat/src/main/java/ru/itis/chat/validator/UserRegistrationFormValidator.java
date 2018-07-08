package ru.itis.chat.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itis.chat.dto.UserRegistrationForm;
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
            errors.reject("bad.login", "This login is already exist!");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name", "Name field is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty.lastName", "LastName field is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Login field is empty");
    }
}
