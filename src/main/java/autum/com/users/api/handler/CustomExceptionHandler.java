package autum.com.users.api.handler;

import autum.com.users.api.error.ErrorResponse;
import autum.com.users.business.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.Locale;

import static autum.com.users.api.error.ErrorCode.INTERNAL_ERROR;
import static autum.com.users.api.error.ErrorCode.getErrorCode;

@ControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFound(UserNotFoundException e, Locale locale) {
        var code = getErrorCode(e);
        var msg = messageSource.getMessage(code.name(), null, locale);
        return new ErrorResponse(code, msg, null);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse internalError(Throwable e, Locale locale) {
        var msg = messageSource.getMessage(INTERNAL_ERROR.name(), null, locale);
        return new ErrorResponse(INTERNAL_ERROR, msg, null);
    }

//    @ResponseBody
//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    public String handleHttpMediaTypeNotAcceptableException() {
//
//    }
}
