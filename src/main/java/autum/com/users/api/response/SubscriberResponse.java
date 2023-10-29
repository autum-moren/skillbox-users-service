package autum.com.users.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriberResponse {

    private String identifier;
    private String firstName;
    private String lastName;
    private String avatarUrl;
}