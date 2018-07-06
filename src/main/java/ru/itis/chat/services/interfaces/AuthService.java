package ru.itis.chat.services.interfaces;

import org.springframework.security.core.Authentication;
import ru.itis.chat.models.User;


public interface AuthService {
    User getUserByAuthentication(Authentication authentication);
}
