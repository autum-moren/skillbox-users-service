package autum.com.users.api.response.mapper;

import autum.com.users.api.response.CityResponse;
import autum.com.users.business.city.dto.CityDto;
import autum.com.users.infrastructure.mapstruct.MainMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityResponseMapper extends MainMapper<CityDto, CityResponse> {
}