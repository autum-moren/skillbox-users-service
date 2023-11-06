package autum.com.users.api.request;

import autum.com.users.business.user.dto.UserDto;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String about;
    private UserDto.Sex sex;
    private Long birthdayAt;
}