package ru.itis.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.User;

import java.util.List;
import java.util.Optional;

public interface DialogRepository extends JpaRepository<Dialog, Long> {
//    Optional<Dialog> findOneByUsers();
    List<Dialog> findDialogByUsersContains(List<User> users);
    List<Dialog> findDialogByUsersContaining(List<User> users);
    Dialog findOneById(Long id);

}
