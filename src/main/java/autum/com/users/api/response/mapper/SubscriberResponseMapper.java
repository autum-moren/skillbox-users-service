package autum.com.users.api.response.mapper;

import autum.com.users.api.response.SubscriberResponse;
import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriberResponseMapper extends MainMapper<SubscriberDto, SubscriberResponse> {
}