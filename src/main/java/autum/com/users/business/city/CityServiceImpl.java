package autum.com.users.business.city;

import autum.com.users.business.city.exception.CityNotFountException;
import autum.com.users.business.city.dto.CityDto;
import autum.com.users.business.city.repo.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<CityDto> findCityByName(String name, Pageable pageable) {
        return cityRepository.findCityByName(name, pageable);
    }

    @Override
    public CityDto getCity(String identifier) {
        var city = cityRepository.getCity(identifier);
        if (city == null) {
            throw new CityNotFountException();
        }
        return city;
    }
}