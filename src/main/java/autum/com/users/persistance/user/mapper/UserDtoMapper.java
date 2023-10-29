package autum.com.users.persistance.user.mapper;

import autum.com.users.business.user.dto.UserDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import autum.com.users.infrastructure.mapstruct.converter.EnumConverter;
import autum.com.users.persistance.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
interface UserDtoMapper extends MainMapper<User, UserDto>, EnumConverter {

    @Mappings({
            @Mapping(target = "sex", source = "sex", qualifiedByName = "IntegerToEnumSex"),
            @Mapping(target = "status", source = "status", qualifiedByName = "IntegerToEnumStatus"),
    })
    @Override
    UserDto map(User dto);

}