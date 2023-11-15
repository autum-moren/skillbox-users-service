package autum.com.users.persistence.subscriptions.repodb;

import autum.com.users.persistence.subscriptions.entity.Subscriber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public interface SubscriptionRepositoryDb extends PagingAndSortingRepository<Subscriber, Long> {

    @Query(value = "SELECT subscriptions.*, u.identifier, u.first_name, u.last_name, u.status, u.avatar_url " +
            "FROM subscriptions AS subscriptions " +
            "LEFT JOIN users AS u ON subscriptions.to_user_id = u.id " +
            "WHERE subscriptions.user_id = :userId AND state = 1", nativeQuery = true)
    Page<Subscriber> findAllSubscription(Long userId, Pageable pageable);

    @Query(value = "SELECT subscribers.*, u.identifier, u.first_name, u.last_name, u.status, u.avatar_url " +
            "FROM subscriptions AS subscribers " +
            "LEFT JOIN users AS u ON subscribers.user_id = u.id " +
            "WHERE subscribers.to_user_id = :toUserId AND state = 1", nativeQuery = true)
    Page<Subscriber> findAllSubscriber(Long toUserId, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO subscriptions (subscribe_at, state, user_id, to_user_id) " +
            "VALUES (:subscribeAt, 1, :srcId, :dstId)", nativeQuery = true)
    void subscribe(long srcId, long dstId, LocalDateTime subscribeAt);

    @Modifying
    @Transactional
    @Query(value = "UPDATE subscriptions SET state = 2, unsubscribe_at = :unsubscribeAt " +
            "WHERE user_id = :srcId AND to_user_id = :dstId", nativeQuery = true)
    void unsubscribe(long srcId, long dstId, LocalDateTime unsubscribeAt);

    @Query(value = "SELECT true FROM subscriptions WHERE user_id = :srcId AND to_user_id = :dstId", nativeQuery = true)
    boolean hasSubscribe(long srcId, long dstId);
}