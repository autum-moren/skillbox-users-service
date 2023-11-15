package autum.com.users.persistence.subscriptions.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
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