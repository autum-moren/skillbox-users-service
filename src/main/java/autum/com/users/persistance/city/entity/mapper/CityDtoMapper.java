package autum.com.users.persistance.city.entity.mapper;

import autum.com.users.business.city.dto.CityDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import autum.com.users.persistance.city.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityDtoMapper extends MainMapper<City, CityDto> {
}