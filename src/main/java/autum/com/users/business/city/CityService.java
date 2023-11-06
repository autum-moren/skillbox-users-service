package autum.com.users.business.city;

import autum.com.users.business.city.dto.CityDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    List<CityDto> findCityByName(String name, Pageable pageable);

    CityDto getCity(String identifier);

}
