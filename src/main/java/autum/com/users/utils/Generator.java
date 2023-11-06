package autum.com.users.utils;

import java.util.UUID;

import static autum.com.users.utils.DateUtil.getTimeNowMillisUTC;

public class Generator {

    public static String generateIdentifier() {
        var time = String.valueOf(getTimeNowMillisUTC());
        return UUID.nameUUIDFromBytes(time.getBytes())
                .toString()
                .replaceAll("-", "")
                .toUpperCase();
    }

}