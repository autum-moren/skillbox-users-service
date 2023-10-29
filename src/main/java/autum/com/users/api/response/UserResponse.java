package autum.com.users.api.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private String identifier;
    private String firstName;
    private String lastName;
    private String middleName;
    private String msisdn;
    private String email;
    private City city;
    private List<Skill> skills;
    private String avatarUrl;
    private String about;
    private Long createdAt;
    private String sex;
    private Long birthdayAt;

    private Long subscriptions;
    private Long subscribers;


    @Getter
    @Setter
    public static class City {
        private String identifier;
        private String name;
        private String region;
        private String country;
    }

    @Getter
    @Setter
    public static class Skill {
        private String identifier;
        private String name;
        private Integer level;
    }
}