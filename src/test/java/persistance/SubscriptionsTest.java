package persistance;

import autum.com.users.business.subscriptions.persistance.SubscriptionRepository;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistance.subscriptions.SubscriptionRepositoryImpl;
import autum.com.users.persistance.subscriptions.repodb.SubscriptionRepositoryDb;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;

public class SubscriptionsTest {

    @Test
    public void getSubscriptions() {
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, mapper);


    }

    @Test
    public void getSubscribers() {
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, mapper);
    }

    @Test
    public void hasSubscribe_true() {
        var srcId = 1;
        var dstId = 2;
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, null);

        Mockito.when(repo.hasSubscribe(srcId, dstId))
                .thenReturn(true);

        var result = subscriptionRepository.hasSubscribe(srcId, dstId);
        assertTrue(result);

        Mockito.verify(repo, Mockito.times(1))
                .hasSubscribe(srcId, dstId);
    }

    @Test
    public void hasSubscribe_false() {
        var srcId = 1;
        var dstId = 2;
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, null);

        Mockito.when(repo.hasSubscribe(srcId, dstId))
                .thenReturn(false);

        var result = subscriptionRepository.hasSubscribe(srcId, dstId);
        assertFalse(result);

        Mockito.verify(repo, Mockito.times(1))
                .hasSubscribe(srcId, dstId);
    }

    @Test
    public void subscribe() {
        var srcId = 1;
        var dstId = 2;
        var time = LocalDateTime.now();
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, null);

        doNothing().when(repo).subscribe(srcId, dstId, time);

        subscriptionRepository.subscribe(srcId, dstId, time);

        Mockito.verify(repo, Mockito.times(1))
                .subscribe(srcId, dstId, time);
    }

    @Test
    public void unsubscribe() {
        var srcId = 1;
        var dstId = 2;
        var time = LocalDateTime.now();
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, null);

        doNothing().when(repo).unsubscribe(srcId, dstId, time);

        subscriptionRepository.unsubscribe(srcId, dstId, time);

        Mockito.verify(repo, Mockito.times(1))
                .unsubscribe(srcId, dstId, time);
    }

}
