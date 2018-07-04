package ru.itis.chat.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.UserRepository;
import ru.itis.chat.services.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


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
}
