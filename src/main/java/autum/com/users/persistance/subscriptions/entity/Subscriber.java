package autum.com.users.persistance.subscriptions.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscriber {

    @Id
    private Long id;
    private String identifier;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Integer status;
    private LocalDateTime subscribeAt;
    private LocalDateTime unsubscribeAt;
    private Integer state;
}