package ru.itis.chat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "messages")
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_mes;

    @NotNull
    @Column(name = "value")
    private String value;

}
