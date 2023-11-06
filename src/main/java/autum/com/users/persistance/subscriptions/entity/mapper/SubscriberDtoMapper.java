package autum.com.users.persistance.subscriptions.entity.mapper;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import autum.com.users.infrastructure.mapstruct.converter.EnumConverter;
import autum.com.users.persistance.subscriptions.entity.Subscriber;
import autum.com.users.persistance.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubscriberDtoMapper extends MainMapper<Subscriber, SubscriberDto>, EnumConverter {

    @Mappings({
            @Mapping(target = "status", source = "status", qualifiedByName = "IntegerToEnumStatus"),
            @Mapping(target = "state", source = "state", qualifiedByName = "IntegerToEnumState")
    })
    SubscriberDto map(Subscriber subscriber);
}