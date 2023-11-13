package autum.com.users.persistance.user;

import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.dto.UserListDto;
import autum.com.users.business.user.persistance.UserRepository;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistance.user.entity.User;
import autum.com.users.persistance.user.repodb.UserRepositoryDb;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryDb repositoryDb;
    private final Mapper mapper;


    @Override
    public UserDto getUserWithSubsCount(String identifier) {
        var user = repositoryDb.findByIdentifierWithSubscrCount(identifier);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUser(String identifier) {
        var user = repositoryDb.findByIdentifier(identifier);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserListDto getUserListByName(String name, Pageable pageable) {
        var pages = repositoryDb.findByName(preparationFullTextSearch(name), pageable);
        return UserListDto.builder()
                .totalCount(pages.getTotalElements())
                .users(pages.get()
                        .map(user -> mapper.map(user, UserDto.class))
                        .collect(Collectors.toList())
                )
                .build();
    }

    private String preparationFullTextSearch(String text) {
        StringBuilder result = new StringBuilder();
        for (String str : text.split("[ ,;._]")) {
            result.append("'").append(str.replace("'", "\\'")).append("':*&");
        }
        return result.toString().replaceAll("&$", "");
    }

    @Override
    public boolean existsByEmail(String email) {
        return repositoryDb.existsByEmail(email);
    }

    @Override
    public UserDto saveUser(UserDto dto) {
        var user = mapper.map(dto, User.class);
        var savedUser = repositoryDb.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getUserListByIdentifier(Set<String> identifiers) {
        return repositoryDb.findAllByIdentifierIn(identifiers)
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}