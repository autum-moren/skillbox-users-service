package autum.com.users.business.subscriptions.persistance;

import autum.com.users.business.subscriptions.dto.SubscriberDto;

import java.util.List;

public interface SubscriptionRepository {

    List<SubscriberDto> getSubscriptions(Long id);

    List<SubscriberDto> getSubscribers(Long userId);
}
