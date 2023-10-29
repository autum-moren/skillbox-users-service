package autum.com.users.business.subscriptions;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.subscriptions.persistance.SubscriptionRepository;
import autum.com.users.business.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Override
    public void subscribe(String identifier, String targetIdentifier) {

    }

    @Override
    public void unsubscribe(String identifier, String targetIdentifier) {

    }

    @Override
    public List<SubscriberDto> getSubscribers(String identifier, Pageable pageable) {
        var user = userService.getUser(identifier);
        return subscriptionRepository.getSubscribers(user.getId());
        //TODO если пользователь из списка заблокирован/деактивирован необходимо заменить avatar_url на (X_X)
    }

    @Override
    public List<SubscriberDto> getSubscriptions(String identifier, Pageable pageable) {
        var user = userService.getUser(identifier);
        return subscriptionRepository.getSubscriptions(user.getId());
        //TODO если пользователь из списка заблокирован/деактивирован необходимо заменить avatar_url на (X_X)

    }
}
