package persistence;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.subscriptions.persistance.SubscriptionRepository;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistence.subscriptions.SubscriptionRepositoryImpl;
import autum.com.users.persistence.subscriptions.entity.Subscriber;
import autum.com.users.persistence.subscriptions.repodb.SubscriptionRepositoryDb;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

public class SubscriptionsTest {

    @Test
    public void getSubscriptions() {
        var userId = 1L;
        var pageable = PageRequest.of(0, 10);
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, mapper);

        var subscription = new Subscriber();
        subscription.setId(1L);
        subscription.setIdentifier("FGTRUITIUY78567VCFDE456OPIU678FG");
        subscription.setFirstName("Olgerd");
        subscription.setLastName("Ryi");
        subscription.setAvatarUrl("https://patykids.ru/samye-krutye-avy-top-260-b991ae9.jpg");
        subscription.setStatus(1);
        subscription.setState(1);
        subscription.setSubscribeAt(LocalDateTime.now());

        var subscription2 = new Subscriber();
        subscription2.setId(2L);
        subscription2.setIdentifier("111RUITIUY78567VCFDE456OPIU678FG");
        subscription2.setFirstName("Olg");
        subscription2.setLastName("Ryir");
        subscription2.setAvatarUrl("https://patykids.ru/samye-krutye.jpg");
        subscription2.setStatus(1);
        subscription2.setState(1);
        subscription2.setSubscribeAt(LocalDateTime.now());

        var page = new PageImpl<>(List.of(subscription, subscription2), pageable, 1);

        Mockito.when(repo.findAllSubscription(userId, pageable))
                .thenReturn(page);

        var subscriptionDto = new SubscriberDto();
        subscriptionDto.setId(1L);
        subscriptionDto.setIdentifier("FGTRUITIUY78567VCFDE456OPIU678FG");
        subscriptionDto.setFirstName("Olgerd");
        subscriptionDto.setLastName("Ryi");
        subscriptionDto.setAvatarUrl("https://patykids.ru/samye-krutye-avy-top-260-b991ae9.jpg");
        subscriptionDto.setStatus(UserDto.Status.ACTIVE);
        subscriptionDto.setState(SubscriberDto.State.SUBSCRIBE);
        subscriptionDto.setSubscribeAt(LocalDateTime.now());

        Mockito.when(mapper.map(subscription, SubscriberDto.class))
                .thenReturn(subscriptionDto);

        var subscription2Dto = new SubscriberDto();
        subscription2Dto.setId(2L);
        subscription2Dto.setIdentifier("111RUITIUY78567VCFDE456OPIU678FG");
        subscription2Dto.setFirstName("Olg");
        subscription2Dto.setLastName("Ryir");
        subscription2Dto.setAvatarUrl("https://patykids.ru/samye-krutye.jpg");
        subscription2Dto.setStatus(UserDto.Status.ACTIVE);
        subscription2Dto.setState(SubscriberDto.State.SUBSCRIBE);
        subscription2Dto.setSubscribeAt(LocalDateTime.now());

        Mockito.when(mapper.map(subscription2, SubscriberDto.class))
                .thenReturn(subscription2Dto);

        var result = subscriptionRepository.getSubscriptions(userId, pageable);
        assertEquals(List.of(subscriptionDto, subscription2Dto), result);

        Mockito.verify(repo, Mockito.times(1))
                .findAllSubscription(userId, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(subscription, SubscriberDto.class);
        Mockito.verify(mapper, Mockito.times(1))
                .map(subscription2, SubscriberDto.class);


    }

    @Test
    public void getSubscribers() {
        var userId = 1L;
        var pageable = PageRequest.of(0, 10);
        var repo = Mockito.mock(SubscriptionRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        SubscriptionRepository subscriptionRepository = new SubscriptionRepositoryImpl(repo, mapper);

        var subscriber = new Subscriber();
        subscriber.setId(1L);
        subscriber.setIdentifier("FGTRUITIUY78567VCFDE456OPIU678FG");
        subscriber.setFirstName("Olgerd");
        subscriber.setLastName("Ryi");
        subscriber.setAvatarUrl("https://patykids.ru/samye-krutye-avy-top-260-b991ae9.jpg");
        subscriber.setStatus(1);
        subscriber.setState(1);
        subscriber.setSubscribeAt(LocalDateTime.now());

        var subscriber2 = new Subscriber();
        subscriber2.setId(2L);
        subscriber2.setIdentifier("111RUITIUY78567VCFDE456OPIU678FG");
        subscriber2.setFirstName("Olg");
        subscriber2.setLastName("Ryir");
        subscriber2.setAvatarUrl("https://patykids.ru/samye-krutye.jpg");
        subscriber2.setStatus(1);
        subscriber2.setState(1);
        subscriber2.setSubscribeAt(LocalDateTime.now());

        var page = new PageImpl<>(List.of(subscriber, subscriber2), pageable, 1);

        Mockito.when(repo.findAllSubscriber(userId, pageable))
                .thenReturn(page);

        var subscriberDto = new SubscriberDto();
        subscriberDto.setId(1L);
        subscriberDto.setIdentifier("FGTRUITIUY78567VCFDE456OPIU678FG");
        subscriberDto.setFirstName("Olgerd");
        subscriberDto.setLastName("Ryi");
        subscriberDto.setAvatarUrl("https://patykids.ru/samye-krutye-avy-top-260-b991ae9.jpg");
        subscriberDto.setStatus(UserDto.Status.ACTIVE);
        subscriberDto.setState(SubscriberDto.State.SUBSCRIBE);
        subscriberDto.setSubscribeAt(LocalDateTime.now());

        Mockito.when(mapper.map(subscriber, SubscriberDto.class))
                .thenReturn(subscriberDto);

        var subscriber2Dto = new SubscriberDto();
        subscriber2Dto.setId(2L);
        subscriber2Dto.setIdentifier("111RUITIUY78567VCFDE456OPIU678FG");
        subscriber2Dto.setFirstName("Olg");
        subscriber2Dto.setLastName("Ryir");
        subscriber2Dto.setAvatarUrl("https://patykids.ru/samye-krutye.jpg");
        subscriber2Dto.setStatus(UserDto.Status.ACTIVE);
        subscriber2Dto.setState(SubscriberDto.State.SUBSCRIBE);
        subscriber2Dto.setSubscribeAt(LocalDateTime.now());

        Mockito.when(mapper.map(subscriber2, SubscriberDto.class))
                .thenReturn(subscriber2Dto);

        var result = subscriptionRepository.getSubscribers(userId, pageable);
        assertEquals(List.of(subscriberDto, subscriber2Dto), result);

        Mockito.verify(repo, Mockito.times(1))
                .findAllSubscriber(userId, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(subscriber, SubscriberDto.class);
        Mockito.verify(mapper, Mockito.times(1))
                .map(subscriber2, SubscriberDto.class);
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
