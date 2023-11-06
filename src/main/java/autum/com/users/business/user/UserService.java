package autum.com.users.business.user;

import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.business.user.dto.UpdateUserDto;
import autum.com.users.business.user.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto getUser(String identifier);

    void createUser(CreateUserDto dto);

    void updateUser(UpdateUserDto dto, String identifier);

    void deactivateUser(String identifier);

    List<UserDto> getActiveUserList(Set<String> identifiers);

}