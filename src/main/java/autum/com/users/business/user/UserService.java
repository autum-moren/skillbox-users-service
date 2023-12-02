package autum.com.users.business.user;

import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.business.user.dto.UpdateUserDto;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.dto.UserListDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto getUserWithCount(String identifier);

    UserDto getUser(String identifier);

    UserListDto getUserListByName(String name, Pageable pageable);

    void createUser(CreateUserDto dto);

    void updateUser(UpdateUserDto dto, String identifier);

    void deactivateUser(String identifier);

    List<UserDto> getActiveUserList(Set<String> identifiers);

}