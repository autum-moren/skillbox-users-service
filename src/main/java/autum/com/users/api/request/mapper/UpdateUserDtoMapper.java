package autum.com.users.api.request.mapper;

import autum.com.users.api.request.UpdateUserRequest;
import autum.com.users.business.user.dto.UpdateUserDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import autum.com.users.infrastructure.mapstruct.converter.TimeConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpdateUserDtoMapper extends MainMapper<UpdateUserRequest, UpdateUserDto>, TimeConverter {

    @Override
    @Mapping(target = "birthdayAt", source = "birthdayAt", qualifiedByName = "LongToLocalDateTime")
    UpdateUserDto map(UpdateUserRequest request);
}