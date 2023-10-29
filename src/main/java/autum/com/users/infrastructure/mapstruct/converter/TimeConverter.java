package autum.com.users.infrastructure.mapstruct.converter;


import autum.com.users.utils.DateUtil;
import org.mapstruct.Named;

import java.time.LocalDateTime;

public interface TimeConverter {

    @Named("LocalDateTimeToLong")
    default Long localDateTimeToLong(LocalDateTime time) {
        return time == null ? null : DateUtil.getMillisFromLocalDateTime(time);
    }

    @Named("LongToLocalDateTime")
    default LocalDateTime longToLocalDateTime(Long time) {
        return time == null ? null : DateUtil.getLocalDateTimeUTC(time);
    }
}