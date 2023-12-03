package autum.com.users.api.handler;

import autum.com.users.api.error.ErrorResponse;
import autum.com.users.business.user.exception.UserDeactivatedException;
import autum.com.users.business.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.Locale;
import java.util.Map;

import static autum.com.users.api.error.ErrorCode.INTERNAL_ERROR;
import static autum.com.users.api.error.ErrorCode.getErrorCode;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFound(UserNotFoundException e, Locale locale) {
        log.debug("user not found exception");
        var code = getErrorCode(e);
        var msg = messageSource.getMessage(code.name(), null, locale);
        return new ErrorResponse(code, msg, null);
    }

    //TODO можно вместо ошибки вернуть недозаполненного пользователя со статусом deactivated
    @ExceptionHandler(UserDeactivatedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST) //TODO можно и 200 вернуть
    public ErrorResponse deactivateUser(UserDeactivatedException e, Locale locale) {
        log.debug("user {} {} deactivated", e.getFirstName(), e.getLastName());
        var code = getErrorCode(e);
        var msg = messageSource.getMessage(code.name(), null, locale);
        return new ErrorResponse(code, msg, Map.of("firstName", e.getFirstName(), "lastName", e.getLastName()));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse internalError(Throwable e, Locale locale) {
        e.printStackTrace();
        var msg = messageSource.getMessage(INTERNAL_ERROR.name(), null, locale);
        return new ErrorResponse(INTERNAL_ERROR, msg, null);
    }

//    @ResponseBody
//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    public String handleHttpMediaTypeNotAcceptableException() {
//
//    }
}
