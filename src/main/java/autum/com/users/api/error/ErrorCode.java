package autum.com.users.api.error;

import autum.com.users.business.user.exception.UserDeactivatedException;
import autum.com.users.business.user.exception.UserNotFoundException;

public enum ErrorCode {
    USER_NOT_FOUND,
    USER_DEACTIVATED,
    INTERNAL_ERROR;

    public static ErrorCode getErrorCode(Throwable e) {
        if(e instanceof UserNotFoundException) {
            return USER_NOT_FOUND;
        } else if(e instanceof UserDeactivatedException) {
            return USER_DEACTIVATED;
        } else {
            return INTERNAL_ERROR;
        }
    }
}