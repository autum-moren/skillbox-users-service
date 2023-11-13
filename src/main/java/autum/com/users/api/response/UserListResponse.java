package autum.com.users.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListResponse {

    private long totalCount;
    private List<ShortUserResponse> users;
}
