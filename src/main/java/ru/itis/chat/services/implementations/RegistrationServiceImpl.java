package ru.itis.chat.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chat.exceptions.EmailExistsException;
import ru.itis.chat.form.UserRegistrationForm;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.UserRepository;
import ru.itis.chat.security.Role.Role;
import ru.itis.chat.services.interfaces.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createUserAccount(UserRegistrationForm userRegistrationForm) throws EmailExistsException {
        if (!userRepository.findFirstByLogin(userRegistrationForm.getLogin()).isPresent()){
            User user = User.builder()
                    .name(userRegistrationForm.getName())
                    .lastName(userRegistrationForm.getLastName())
                    .login(userRegistrationForm.getLogin())
                    .hashPassword(passwordEncoder.encode(userRegistrationForm.getPassword()))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
        } else throw new EmailExistsException("There is an account with that login:" + userRegistrationForm.getLogin());
    }
}
