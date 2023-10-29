package autum.com.users.persistance.user;

import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.persistance.UserRepository;
import autum.com.users.infrastructure.mapstruct.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryDb repositoryDb;
    private final Mapper mapper;


    @Override
    public UserDto getUser(String identifier) {
        var user = repositoryDb.findByIdentifierWithSubscrCount(identifier);
        return mapper.map(user, UserDto.class);
    }
}