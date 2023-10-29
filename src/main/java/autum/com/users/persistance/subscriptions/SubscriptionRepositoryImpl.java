package autum.com.users.persistance.subscriptions;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.subscriptions.persistance.SubscriptionRepository;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistance.subscriptions.repodb.SubscriptionRepositoryDb;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionRepositoryDb subscriptionRepository;
    private final Mapper mapper;

    @Override
    public List<SubscriberDto> getSubscriptions(Long userId) {
        var list = subscriptionRepository.findAllSubscription(userId);
        return list.stream()
                .map(subscription -> mapper.map(subscription, SubscriberDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SubscriberDto> getSubscribers(Long userId) {
        var list = subscriptionRepository.findAllSubscriber(userId);
        return list.stream()
                .map(subscriber -> mapper.map(subscriber, SubscriberDto.class))
                .collect(Collectors.toList());
    }
}
