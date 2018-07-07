package ru.itis.chat.models;

import lombok.*;
import ru.itis.chat.security.Role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_dialog", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dialog_id", referencedColumnName = "id"))
    private List<Dialog> dialogs = new ArrayList<>();



}