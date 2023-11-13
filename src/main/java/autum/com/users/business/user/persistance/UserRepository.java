package autum.com.users.business.user.persistance;

import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.dto.UserListDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    UserDto getUser(String identifier);

    UserListDto getUserListByName(String name, Pageable pageable);

    UserDto getUserWithSubsCount(String identifier);

    boolean existsByEmail(String email);

    UserDto saveUser(UserDto dto);

    List<UserDto> getUserListByIdentifier(Set<String> identifiers);
}