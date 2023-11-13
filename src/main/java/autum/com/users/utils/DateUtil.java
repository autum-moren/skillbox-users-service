package autum.com.users.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateUtil {

    public static long getTimeNowMillis() {
        return System.currentTimeMillis();
    }

    public static long getTimeNowMillisUTC() {
        return Instant.ofEpochMilli(getTimeNowMillis())
                .atZone(ZoneId.of("UTC"))
                .toInstant()
                .toEpochMilli();
    }

    public static LocalDateTime getLocalDateTimeUTCNow() {
        return Instant.ofEpochMilli(getTimeNowMillis())
                .atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
    }

    public static LocalDateTime getLocalDateTimeUTC(long millis) {
        return Instant.ofEpochMilli(millis)
                .atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
    }

    public static Long getMillisFromLocalDateTime(LocalDateTime time) {
        return time.toInstant(ZoneOffset.UTC)
                .toEpochMilli();
    }
}