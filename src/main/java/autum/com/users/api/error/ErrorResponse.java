package autum.com.users.api.error;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private ErrorCode errorCode;
    private String message;
    private Map<String, String> details;

}