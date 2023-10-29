package autum.com.users.business.subscriptions;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubscribeService {

    void subscribe(String identifier, String targetIdentifier);

    void unsubscribe(String identifier, String targetIdentifier);

    List<SubscriberDto> getSubscribers(String identifier, Pageable pageable);

    List<SubscriberDto> getSubscriptions(String identifier, Pageable pageable);

}