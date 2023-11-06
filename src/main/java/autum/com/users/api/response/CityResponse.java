package autum.com.users.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {

    private String identifier;
    private String name;
    private String region;
    private String country;
}