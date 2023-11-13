package autum.com.users.persistance.user.entity;

import autum.com.users.persistance.city.entity.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String identifier;
    private String firstName;
    private String lastName;
    private String middleName;
    private String msisdn;
    private String email;
    @OneToOne
    private City city;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Skill> skills;
    private String avatarUrl;
    private String about;
    private LocalDateTime createdAt;
    private LocalDateTime deactivatedAt;
    //TODO blockedAt?
    private Integer sex;
    private Integer status;
    private LocalDateTime birthdayAt;

    @Transient
    private Long subscriptions;
    @Transient
    private Long subscribers;
}