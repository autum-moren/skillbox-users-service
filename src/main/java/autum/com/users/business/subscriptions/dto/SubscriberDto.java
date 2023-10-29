package autum.com.users.business.subscriptions.dto;

import autum.com.users.business.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SubscriberDto {

    private Long id;
    private String identifier;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private UserDto.Status status;
    private LocalDateTime subscribeAt;
    private LocalDateTime unsubscribeAt;
    private State state;

    public enum State {
        SUBSCRIBE,
        UNSUBSCRIBE,
        DEACTIVATED,
        DELETED;

        public static State fromInteger(int status) {
            switch (status) {
                case 1:
                    return SUBSCRIBE;
                case 2:
                    return UNSUBSCRIBE;
                case 3:
                    return DEACTIVATED;
                case 4:
                    return DELETED;
                default:
                    throw new IllegalArgumentException("Integer " + status + "not processed!");
            }
        }

        public static int toInteger(State state) {
            switch (state) {
                case SUBSCRIBE:
                    return 1;
                case UNSUBSCRIBE:
                    return 2;
                case DEACTIVATED:
                    return 3;
                case DELETED:
                    return 4;
                default:
                    throw new IllegalArgumentException("Status " + state + "not processed!");
            }
        }
    }
}