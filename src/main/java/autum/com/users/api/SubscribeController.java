package autum.com.users.api;

import autum.com.users.api.response.ShortUserResponse;
import autum.com.users.business.subscriptions.SubscribeService;
import autum.com.users.infrastructure.mapstruct.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/subscriptions/user")
public class SubscribeController {

    private final SubscribeService subscribeService;
    private final Mapper mapper;


    @PostMapping("/{identifier}/subscribe")
    @ResponseStatus(OK)
    public void subscribe(@PathVariable("identifier") String targetIdentifier, String identifier) {  //TODO в будущем identifier брать из токена
        subscribeService.subscribe(identifier, targetIdentifier);
    }

    @PostMapping("/{identifier}/unsubscribe")
    @ResponseStatus(OK)
    public void unsubscribe(@PathVariable("identifier") String targetIdentifier, String identifier) { //TODO в будущем identifier брать из токена
        subscribeService.unsubscribe(identifier, targetIdentifier);
    }

    @GetMapping("/{identifier}/subscriber/list")
    @ResponseBody
    public List<ShortUserResponse> listSubscriber(@PathVariable String identifier, Pageable pageable) {
        var list = subscribeService.getSubscribers(identifier, pageable);
        return list.stream()
                .map(subscrDto -> mapper.map(subscrDto, ShortUserResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{identifier}/subscription/list")
    @ResponseBody
    public List<ShortUserResponse> listSubscription(@PathVariable String identifier, Pageable pageable) {
        var list = subscribeService.getSubscriptions(identifier, pageable);
        return list.stream()
                .map(user -> mapper.map(user, ShortUserResponse.class))
                .collect(Collectors.toList());
    }
}