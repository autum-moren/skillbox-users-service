package autum.com.users.api.request;

import lombok.Getter;

@Getter
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String msisdn;
    private String email;
    private String sex;
    private Long birthdayAt;
}