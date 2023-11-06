package autum.com.users.business.city.repo;

import autum.com.users.business.city.dto.CityDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityRepository {

    CityDto getCity(String identifier);

    List<CityDto> findCityByName(String name, Pageable pageable);
}