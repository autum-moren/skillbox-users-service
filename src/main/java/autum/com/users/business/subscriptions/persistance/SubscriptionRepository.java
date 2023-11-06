package autum.com.users.business.subscriptions.persistance;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepository {

    List<SubscriberDto> getSubscriptions(Long id, Pageable pageable);

    List<SubscriberDto> getSubscribers(Long userId, Pageable pageable);

    boolean hasSubscribe(long srcId, long dstId);

    void subscribe(long srcId, long dstId, LocalDateTime subscribeAt);

    void unsubscribe(long srcId, long dstId, LocalDateTime unsubscribeAt);
}
