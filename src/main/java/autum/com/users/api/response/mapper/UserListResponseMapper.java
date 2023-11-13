package autum.com.users.api.response.mapper;

import autum.com.users.api.response.UserListResponse;
import autum.com.users.business.user.dto.UserListDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserListResponseMapper extends MainMapper<UserListDto, UserListResponse> {
}
