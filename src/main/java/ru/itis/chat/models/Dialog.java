package ru.itis.chat.models;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dialogs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"users", "messages"})
@EqualsAndHashCode(exclude = {"users", "messages"})
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_dialog", joinColumns = @JoinColumn(name = "dialog_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "dialog", cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<Message>();


//    @Override
//    public int hashCode() {
//        Integer result = id;
//        result = 31 * result + (title != null ? title.hashCode() : 0);
//        return result;
//    }

}
