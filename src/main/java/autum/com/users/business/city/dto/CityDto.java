package autum.com.users.business.city.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CityDto {

    private Long id;
    private String identifier;
    private String name;
    private String region;
    private String country;
}