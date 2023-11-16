package api;

import autum.com.users.api.SubscribeController;
import autum.com.users.api.response.ShortUserResponse;
import autum.com.users.business.subscriptions.SubscribeService;
import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.infrastructure.mapstruct.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

public class SubscribeControllerTest {

    @Test
    public void subscribe() {
        var srcIdentifier = "FGTUIRERTOPVCXSDE89076ZXDERW54F9";
        var dstIdentifier = "111UIRERTOPVCXSDE89076ZXDERW54F9";

        var subscribeService = Mockito.mock(SubscribeService.class);

        doNothing().when(subscribeService)
                .subscribe(dstIdentifier, srcIdentifier);

        var subscribeController = new SubscribeController(subscribeService, null);

        subscribeController.subscribe(srcIdentifier, dstIdentifier);

        Mockito.verify(subscribeService, Mockito.times(1))
                .subscribe(dstIdentifier, srcIdentifier);
    }

    @Test
    public void unsubscribe() {
        var srcIdentifier = "FGTUIRERTOPVCXSDE89076ZXDERW54F9";
        var dstIdentifier = "111UIRERTOPVCXSDE89076ZXDERW54F9";

        var subscribeService = Mockito.mock(SubscribeService.class);

        doNothing().when(subscribeService)
                .unsubscribe(srcIdentifier, dstIdentifier);

        var subscribeController = new SubscribeController(subscribeService, null);

        subscribeController.unsubscribe(dstIdentifier, srcIdentifier);

        Mockito.verify(subscribeService, Mockito.times(1))
                .unsubscribe(srcIdentifier, dstIdentifier);
    }

    @Test
    public void listSubscriber() {
        var identifier = "FGTUIRERTOPVCXSDE89076ZXDERW54F9";
        var pageable = PageRequest.of(0, 10);

        var subscribeService = Mockito.mock(SubscribeService.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var subscriberDto = new SubscriberDto();
        subscriberDto.setId(1L);
        subscriberDto.setIdentifier("1112IRERTOPVCXSDE89076ZXDERW54F9");
        subscriberDto.setFirstName("Olgerd");
        subscriberDto.setLastName("Ryan");
        subscriberDto.setSubscribeAt(time);
        subscriberDto.setStatus(UserDto.Status.ACTIVE);
        subscriberDto.setState(SubscriberDto.State.SUBSCRIBE);
        subscriberDto.setAvatarUrl("https://android-obzor.com/man-silhouette.jpg");

        Mockito.when(subscribeService.getSubscribers(identifier, pageable))
                .thenReturn(List.of(subscriberDto));

        var shortUser = new ShortUserResponse();
        shortUser.setIdentifier(subscriberDto.getIdentifier());
        shortUser.setFirstName(subscriberDto.getFirstName());
        shortUser.setLastName(subscriberDto.getLastName());
        shortUser.setAvatarUrl(subscriberDto.getAvatarUrl());

        Mockito.when(mapper.map(subscriberDto, ShortUserResponse.class))
                .thenReturn(shortUser);

        var subscribeController = new SubscribeController(subscribeService, mapper);

        var result = subscribeController.listSubscriber(identifier, pageable);
        assertEquals(List.of(shortUser), result);

        Mockito.verify(subscribeService, Mockito.times(1))
                .getSubscribers(identifier, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(subscriberDto, ShortUserResponse.class);
    }

    @Test
    public void listSubscription() {
        var identifier = "FGTUIRERTOPVCXSDE89076ZXDERW54F9";
        var pageable = PageRequest.of(0, 10);

        var subscribeService = Mockito.mock(SubscribeService.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var subscriberDto = new SubscriberDto();
        subscriberDto.setId(1L);
        subscriberDto.setIdentifier("1112IRERTOPVCXSDE89076ZXDERW54F9");
        subscriberDto.setFirstName("Olgerd");
        subscriberDto.setLastName("Ryan");
        subscriberDto.setSubscribeAt(time);
        subscriberDto.setStatus(UserDto.Status.ACTIVE);
        subscriberDto.setState(SubscriberDto.State.SUBSCRIBE);
        subscriberDto.setAvatarUrl("https://android-obzor.com/man-silhouette.jpg");

        Mockito.when(subscribeService.getSubscriptions(identifier, pageable))
                .thenReturn(List.of(subscriberDto));

        var shortUser = new ShortUserResponse();
        shortUser.setIdentifier(subscriberDto.getIdentifier());
        shortUser.setFirstName(subscriberDto.getFirstName());
        shortUser.setLastName(subscriberDto.getLastName());
        shortUser.setAvatarUrl(subscriberDto.getAvatarUrl());

        Mockito.when(mapper.map(subscriberDto, ShortUserResponse.class))
                .thenReturn(shortUser);

        var subscribeController = new SubscribeController(subscribeService, mapper);

        var result = subscribeController.listSubscription(identifier, pageable);
        assertEquals(List.of(shortUser), result);

        Mockito.verify(subscribeService, Mockito.times(1))
                .getSubscriptions(identifier, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(subscriberDto, ShortUserResponse.class);
    }
}
