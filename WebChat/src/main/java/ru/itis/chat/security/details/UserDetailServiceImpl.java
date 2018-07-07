package ru.itis.chat.security.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.chat.models.User;
import ru.itis.chat.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

   private UserRepository userRepository;

    private UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findFirstByLogin(login).orElseThrow(() ->
                new IllegalArgumentException("User not found by <"+ login+">"));
        return new UserDetailsImpl(user);
    }
}
