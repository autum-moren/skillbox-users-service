package autum.com.users.api.response.mapper;

import autum.com.users.api.response.UserResponse;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import autum.com.users.infrastructure.mapstruct.converter.TimeConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserResponseMapper extends MainMapper<UserDto, UserResponse>, TimeConverter {

    @Mappings({
            @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "LocalDateTimeToLong"),
            @Mapping(target = "birthdayAt", source = "birthdayAt", qualifiedByName = "LocalDateTimeToLong")
    })
    @Override
    UserResponse map(UserDto dto);
}