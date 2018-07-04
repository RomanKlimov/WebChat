package ru.itis.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.chat.models.Dialog;
import ru.itis.chat.models.Message;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Set<Message> findAllByDialog(Dialog dialog);
}

