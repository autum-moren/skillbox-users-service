package autum.com.users.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static long getTimeNowMillis() {
        return System.currentTimeMillis();
    }

    public static long getTimeMillisAfterSec(long second) {
        return getTimeNowMillis() + secondToMillis(second);
    }

    public static long getRemainingTimeMillis(long millis) {
        return millis - getTimeNowMillis();
    }

    public static long millisToSecond(long millis) {
        return TimeUnit.MILLISECONDS.toSeconds(millis);
    }

    public static long secondToMillis(long second) {
        return TimeUnit.SECONDS.toMillis(second);
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