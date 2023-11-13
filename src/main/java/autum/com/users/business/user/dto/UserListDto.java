package autum.com.users.business.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserListDto {

    private long totalCount;
    private List<UserDto> users;
}
