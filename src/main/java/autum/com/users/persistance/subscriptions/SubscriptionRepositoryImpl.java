package autum.com.users.persistance.subscriptions;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.subscriptions.persistance.SubscriptionRepository;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistance.subscriptions.repodb.SubscriptionRepositoryDb;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionRepositoryDb subscriptionRepository;
    private final Mapper mapper;

    @Override
    public List<SubscriberDto> getSubscriptions(Long userId, Pageable pageable) {
        var list = subscriptionRepository.findAllSubscription(userId, pageable);
        return list.stream()
                .map(subscription -> mapper.map(subscription, SubscriberDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SubscriberDto> getSubscribers(Long userId, Pageable pageable) {
        var list = subscriptionRepository.findAllSubscriber(userId, pageable);
        return list.stream()
                .map(subscriber -> mapper.map(subscriber, SubscriberDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasSubscribe(long srcId, long dstId) {
        return subscriptionRepository.hasSubscribe(srcId, dstId);
    }

    @Override
    public void subscribe(long srcId, long dstId, LocalDateTime subscribeAt) {
        subscriptionRepository.subscribe(srcId, dstId, subscribeAt);
    }

    @Override
    public void unsubscribe(long srcId, long dstId, LocalDateTime unsubscribeAt) {
        subscriptionRepository.unsubscribe(srcId, dstId, unsubscribeAt);
    }
}