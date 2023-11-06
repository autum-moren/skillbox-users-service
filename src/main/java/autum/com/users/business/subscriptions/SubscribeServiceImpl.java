package autum.com.users.business.subscriptions;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.subscriptions.exception.UserNotSubscribedException;
import autum.com.users.business.subscriptions.persistance.SubscriptionRepository;
import autum.com.users.business.user.UserService;
import autum.com.users.business.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static autum.com.users.utils.DateUtil.getLocalDateTimeUTCNow;

@Service
@AllArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Override
    public void subscribe(String srcIdentifier, String dstIdentifier) {
        var map = userService.getActiveUserList(Set.of(srcIdentifier, dstIdentifier)).stream()
                .collect(Collectors.toMap(UserDto::getIdentifier, UserDto::getId));
        var time = getLocalDateTimeUTCNow();
        subscriptionRepository.subscribe(map.get(srcIdentifier), map.get(dstIdentifier), time);
    }

    @Override
    public void unsubscribe(String srcIdentifier, String dstIdentifier) {
        var map = userService.getActiveUserList(Set.of(srcIdentifier, dstIdentifier)).stream()
                .collect(Collectors.toMap(UserDto::getIdentifier, UserDto::getId));
        if (!subscriptionRepository.hasSubscribe(map.get(srcIdentifier), map.get(dstIdentifier))) {
            throw new UserNotSubscribedException();
        }
        var time = getLocalDateTimeUTCNow();
        subscriptionRepository.unsubscribe(map.get(srcIdentifier), map.get(dstIdentifier), time);
    }

    @Override
    public List<SubscriberDto> getSubscribers(String identifier, Pageable pageable) {
        var user = userService.getUser(identifier);
        return subscriptionRepository.getSubscribers(user.getId(), pageable);
        //TODO если пользователь из списка заблокирован/деактивирован необходимо заменить avatar_url на (X_X)
    }

    @Override
    public List<SubscriberDto> getSubscriptions(String identifier, Pageable pageable) {
        var user = userService.getUser(identifier);
        return subscriptionRepository.getSubscriptions(user.getId(), pageable);
        //TODO если пользователь из списка заблокирован/деактивирован необходимо заменить avatar_url на (X_X)
    }
}
