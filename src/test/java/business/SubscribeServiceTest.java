package business;

import autum.com.users.business.city.dto.CityDto;
import autum.com.users.business.subscriptions.SubscribeService;
import autum.com.users.business.subscriptions.SubscribeServiceImpl;
import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.subscriptions.exception.UserNotSubscribedException;
import autum.com.users.business.subscriptions.persistance.SubscriptionRepository;
import autum.com.users.business.user.UserService;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.utils.DateUtil;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubscribeServiceTest {


    @Test
    void subscribe() {
        var srcIdentifier = "IFRTYERT456743VBHGTY876DRET678OI";
        var dstIdentifier = "111TYERT456743VBHGTY876DRET678OI";

        var time = LocalDateTime.now();
        new MockUp<DateUtil>() {
            @Mock
            public LocalDateTime getLocalDateTimeUTCNow() {
                return time;
            }
        };


        var subscriptionRepo = Mockito.mock(SubscriptionRepository.class);
        var userService = Mockito.mock(UserService.class);
        SubscribeService subscribeService = new SubscribeServiceImpl(subscriptionRepo, userService);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("IFRTYERT456743VBHGTY876DRET678OI");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var userDto1 = new UserDto();
        userDto1.setId(2L);
        userDto1.setIdentifier("111TYERT456743VBHGTY876DRET678OI");
        userDto1.setFirstName("Olg");
        userDto1.setLastName("Ruy");
        userDto1.setMiddleName("Omen");
        userDto1.setMsisdn("375291467890");
        userDto1.setEmail("amail@gmail.com");
        userDto1.setCity(city);
        userDto1.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto1.setAbout("wtf");
        userDto1.setCreatedAt(LocalDateTime.now());
        userDto1.setSex(UserDto.Sex.MALE);
        userDto1.setStatus(UserDto.Status.ACTIVE);
        userDto1.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userService.getActiveUserList(Set.of(srcIdentifier, dstIdentifier)))
                .thenReturn(List.of(userDto, userDto1));

        subscribeService.subscribe(srcIdentifier, dstIdentifier);

        Mockito.verify(subscriptionRepo, Mockito.times(1))
                .subscribe(1, 2, time);
        Mockito.verify(userService, Mockito.times(1))
                .getActiveUserList(Set.of(srcIdentifier, dstIdentifier));

    }

    @Test
    void unsubscribe() {
        var dstIdentifier = "IFRTYERT456743VBHGTY876DRET678OI";
        var srcIdentifier = "111TYERT456743VBHGTY876DRET678OI";

        var time = LocalDateTime.now();
        new MockUp<DateUtil>() {
            @Mock
            public LocalDateTime getLocalDateTimeUTCNow() {
                return time;
            }
        };

        var subscriptionRepo = Mockito.mock(SubscriptionRepository.class);
        var userService = Mockito.mock(UserService.class);
        SubscribeService subscribeService = new SubscribeServiceImpl(subscriptionRepo, userService);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("IFRTYERT456743VBHGTY876DRET678OI");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var userDto1 = new UserDto();
        userDto1.setId(2L);
        userDto1.setIdentifier("111TYERT456743VBHGTY876DRET678OI");
        userDto1.setFirstName("Olg");
        userDto1.setLastName("Ruy");
        userDto1.setMiddleName("Omen");
        userDto1.setMsisdn("375291467890");
        userDto1.setEmail("amail@gmail.com");
        userDto1.setCity(city);
        userDto1.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto1.setAbout("wtf");
        userDto1.setCreatedAt(LocalDateTime.now());
        userDto1.setSex(UserDto.Sex.MALE);
        userDto1.setStatus(UserDto.Status.ACTIVE);
        userDto1.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userService.getActiveUserList(Set.of(srcIdentifier, dstIdentifier)))
                .thenReturn(List.of(userDto, userDto1));
        Mockito.when(subscriptionRepo.hasSubscribe(1, 2))
                .thenReturn(true);

        subscribeService.unsubscribe(dstIdentifier, srcIdentifier);

        Mockito.verify(subscriptionRepo, Mockito.times(1))
                .unsubscribe(1, 2, time);
        Mockito.verify(userService, Mockito.times(1))
                .getActiveUserList(Set.of(srcIdentifier, dstIdentifier));
        Mockito.verify(subscriptionRepo, Mockito.times(1))
                .hasSubscribe(1, 2);
    }

    @Test
    void unsubscribe_notFoundSubscribe() {
        var dstIdentifier = "IFRTYERT456743VBHGTY876DRET678OI";
        var srcIdentifier = "111TYERT456743VBHGTY876DRET678OI";
        var subscriptionRepo = Mockito.mock(SubscriptionRepository.class);

        var userService = Mockito.mock(UserService.class);
        SubscribeService subscribeService = new SubscribeServiceImpl(subscriptionRepo, userService);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("IFRTYERT456743VBHGTY876DRET678OI");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var userDto1 = new UserDto();
        userDto1.setId(2L);
        userDto1.setIdentifier("111TYERT456743VBHGTY876DRET678OI");
        userDto1.setFirstName("Olg");
        userDto1.setLastName("Ruy");
        userDto1.setMiddleName("Omen");
        userDto1.setMsisdn("375291467890");
        userDto1.setEmail("amail@gmail.com");
        userDto1.setCity(city);
        userDto1.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto1.setAbout("wtf");
        userDto1.setCreatedAt(LocalDateTime.now());
        userDto1.setSex(UserDto.Sex.MALE);
        userDto1.setStatus(UserDto.Status.ACTIVE);
        userDto1.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userService.getActiveUserList(Set.of(srcIdentifier, dstIdentifier)))
                .thenReturn(List.of(userDto, userDto1));
        Mockito.when(subscriptionRepo.hasSubscribe(2, 1))
                .thenReturn(false);

        assertThrows(UserNotSubscribedException.class, () ->
                subscribeService.unsubscribe("111TYERT456743VBHGTY876DRET678OI", "IFRTYERT456743VBHGTY876DRET678OI"));

        Mockito.verify(userService, Mockito.times(1))
                .getActiveUserList(Set.of(srcIdentifier, dstIdentifier));
        Mockito.verify(subscriptionRepo, Mockito.times(1))
                .hasSubscribe(2, 1);
    }

    @Test
    void getSubscribers() {
        var identifier = "IFRTYERT456743VBHGTY876DRET678OI";
        var subscriptionRepo = Mockito.mock(SubscriptionRepository.class);
        var userService = Mockito.mock(UserService.class);
        var pageable = PageRequest.of(0, 10);

        SubscribeService subscribeService = new SubscribeServiceImpl(subscriptionRepo, userService);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("IFRTYERT456743VBHGTY876DRET678OI");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userService.getUser(identifier))
                .thenReturn(userDto);

        var subscriber1 = new SubscriberDto();
        subscriber1.setId(1L);
        subscriber1.setFirstName("Ony");
        subscriber1.setLastName("Ty");
        subscriber1.setIdentifier("FGHTYREITO345678VCDFRE456GHYTO90");
        subscriber1.setStatus(UserDto.Status.ACTIVE);
        subscriber1.setState(SubscriberDto.State.SUBSCRIBE);

        var subscriber2 = new SubscriberDto();
        subscriber2.setId(2L);
        subscriber2.setFirstName("Odin");
        subscriber2.setLastName("Tyr");
        subscriber2.setIdentifier("111TYREITO345678VCDFRE456GHYTO90");
        subscriber2.setStatus(UserDto.Status.ACTIVE);
        subscriber2.setState(SubscriberDto.State.SUBSCRIBE);

        Mockito.when(subscriptionRepo.getSubscribers(1L, pageable))
                .thenReturn(List.of(subscriber1, subscriber2));

        var result = subscribeService.getSubscribers(identifier, pageable);
        assertEquals(List.of(subscriber1, subscriber2), result);

        Mockito.verify(userService, Mockito.times(1))
                .getUser(identifier);
        Mockito.verify(subscriptionRepo, Mockito.times(1))
                .getSubscribers(1L, pageable);
    }

    @Test
    void getSubscriptions() {
        var identifier = "IFRTYERT456743VBHGTY876DRET678OI";
        var subscriptionRepo = Mockito.mock(SubscriptionRepository.class);
        var userService = Mockito.mock(UserService.class);

        var pageable = PageRequest.of(0, 10);

        SubscribeService subscribeService = new SubscribeServiceImpl(subscriptionRepo, userService);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("IFRTYERT456743VBHGTY876DRET678OI");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userService.getUser(identifier))
                .thenReturn(userDto);

        var subscription1 = new SubscriberDto();
        subscription1.setId(1L);
        subscription1.setFirstName("Ony");
        subscription1.setLastName("Ty");
        subscription1.setIdentifier("FGHTYREITO345678VCDFRE456GHYTO90");
        subscription1.setStatus(UserDto.Status.ACTIVE);
        subscription1.setState(SubscriberDto.State.SUBSCRIBE);

        var subscription2 = new SubscriberDto();
        subscription2.setId(2L);
        subscription2.setFirstName("Odin");
        subscription2.setLastName("Tyr");
        subscription2.setIdentifier("111TYREITO345678VCDFRE456GHYTO90");
        subscription2.setStatus(UserDto.Status.ACTIVE);
        subscription2.setState(SubscriberDto.State.SUBSCRIBE);

        Mockito.when(subscriptionRepo.getSubscriptions(1L, pageable))
                .thenReturn(List.of(subscription1, subscription2));

        var result = subscribeService.getSubscriptions(identifier, pageable);
        assertEquals(List.of(subscription1, subscription2), result);

        Mockito.verify(userService, Mockito.times(1))
                .getUser(identifier);
        Mockito.verify(subscriptionRepo, Mockito.times(1))
                .getSubscriptions(1L, pageable);
    }
}