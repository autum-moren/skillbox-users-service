package autum.com.users.business.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUserDto {

    private String firstName;
    private String lastName;
    private String middleName;
    private String msisdn;
    private String email;
    private UserDto.Sex sex;
    private LocalDateTime birthdayAt;
}
