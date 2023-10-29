package autum.com.users.business.user;

import autum.com.users.business.user.dto.UserDto;

public interface UserService {

    UserDto getUser(String identifier);

}
