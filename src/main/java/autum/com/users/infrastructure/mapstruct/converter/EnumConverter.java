package autum.com.users.infrastructure.mapstruct.converter;

import autum.com.users.business.subscriptions.dto.SubscriberDto;
import autum.com.users.business.user.dto.UserDto;
import org.mapstruct.Named;

public interface EnumConverter {

    @Named("IntegerToEnumSex")
    default UserDto.Sex integerToEnumSex(Integer sex) {
        return sex == null ? null : UserDto.Sex.fromInteger(sex);
    }

    @Named("EnumSexToInteger")
    default Integer enumSexToInteger(UserDto.Sex sex) {
        return sex == null ? null : UserDto.Sex.toInteger(sex);
    }

    @Named("IntegerToEnumStatus")
    default UserDto.Status integerToEnumStatus(Integer status) {
        return status == null ? null : UserDto.Status.fromInteger(status);
    }

    @Named("EnumStatusToInteger")
    default Integer enumStatusToInteger(UserDto.Status status) {
        return status == null ? null : UserDto.Status.toInteger(status);
    }

    @Named("IntegerToEnumState")
    default SubscriberDto.State integerToEnumState(Integer status) {
        return status == null ? null : SubscriberDto.State.fromInteger(status);
    }

    @Named("EnumStateToInteger")
    default Integer enumStateToInteger(SubscriberDto.State state) {
        return state == null ? null : SubscriberDto.State.toInteger(state);
    }
}