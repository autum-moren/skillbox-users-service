package autum.com.users.business.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDeactivatedException extends RuntimeException {

    private final String firstName;
    private final String lastName;

}