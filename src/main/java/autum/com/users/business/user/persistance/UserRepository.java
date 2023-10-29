package autum.com.users.business.user.persistance;

import autum.com.users.business.user.dto.UserDto;

public interface UserRepository {

    UserDto getUser(String identifier);

}
