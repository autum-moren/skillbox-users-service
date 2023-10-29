package autum.com.users.persistance.city;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String identifier;
    private String name;
    private String region;
    private String country;
}