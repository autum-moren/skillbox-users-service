package autum.com.users.persistance.user.entity.mapper;

import autum.com.users.business.user.dto.UserDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import autum.com.users.infrastructure.mapstruct.converter.EnumConverter;
import autum.com.users.persistance.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper extends MainMapper<UserDto, User>, EnumConverter {

    @Mappings({
            @Mapping(target = "sex", source = "sex", qualifiedByName = "EnumSexToInteger"),
            @Mapping(target = "status", source = "status", qualifiedByName = "EnumStatusToInteger"),
    })
    @Override
    User map(UserDto dto);
}