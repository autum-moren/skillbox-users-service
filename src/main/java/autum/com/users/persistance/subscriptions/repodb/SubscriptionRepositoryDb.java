package autum.com.users.persistance.subscriptions.repodb;

import autum.com.users.persistance.subscriptions.entity.Subscriber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscriptionRepositoryDb extends CrudRepository<Subscriber, Long> {

    @Query(value = "SELECT subscriptions.*, u.identifier, u.first_name, u.last_name, u.status, u.avatar_url " +
            "FROM subscriptions AS subscriptions " +
            "LEFT JOIN users AS u ON subscriptions.to_user_id = u.id " +
            "WHERE subscriptions.user_id = :userId AND state <> 3;", nativeQuery = true)
    List<Subscriber> findAllSubscription(Long userId);

    @Query(value = "SELECT subscribers.*, u.identifier, u.first_name, u.last_name, u.status, u.avatar_url " +
            "FROM subscriptions AS subscribers " +
            "LEFT JOIN users AS u ON subscribers.user_id = u.id " +
            "WHERE subscribers.to_user_id = :toUserId AND state <> 4;", nativeQuery = true)
    List<Subscriber> findAllSubscriber(Long toUserId);
}
