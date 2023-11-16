package autum.com.users.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String about;
    private String sex;
    private Long birthdayAt;
}