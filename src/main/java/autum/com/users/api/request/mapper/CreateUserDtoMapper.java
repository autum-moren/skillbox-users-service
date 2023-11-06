package autum.com.users.api.request.mapper;

import autum.com.users.api.request.CreateUserRequest;
import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import autum.com.users.infrastructure.mapstruct.converter.TimeConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateUserDtoMapper extends MainMapper<CreateUserRequest, CreateUserDto>, TimeConverter {

    @Override
    @Mapping(target = "birthdayAt", source = "birthdayAt", qualifiedByName = "LongToLocalDateTime")
    CreateUserDto map(CreateUserRequest request);
}