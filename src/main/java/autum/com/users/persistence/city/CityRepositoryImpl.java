package autum.com.users.persistence.city;

import autum.com.users.business.city.repo.CityRepository;
import autum.com.users.business.city.dto.CityDto;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistence.city.repodb.CityRepositoryDb;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final CityRepositoryDb cityRepositoryDb;
    private final Mapper mapper;

    @Override
    public CityDto getCity(String identifier) {
       var city = cityRepositoryDb.findByIdentifier(identifier);
        return mapper.map(city, CityDto.class);
    }

    //TODO все же totalCount нужно отдать на UI
    @Override
    public List<CityDto> findCityByName(String name, Pageable pageable) {
        return cityRepositoryDb.findAllByNameLike(name, pageable).stream()
                .map(city -> mapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }
}