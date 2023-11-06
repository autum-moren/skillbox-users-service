package autum.com.users.business.user.persistance;

import autum.com.users.business.user.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    UserDto getUser(String identifier);

    UserDto getUserWithSubsCount(String identifier);

    boolean existsByEmail(String email);

    void saveUser(UserDto dto);

    List<UserDto> getUserListByIdentifier(Set<String> identifiers);
}