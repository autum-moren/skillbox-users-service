package api;


import autum.com.users.api.CityController;
import autum.com.users.api.response.CityResponse;
import autum.com.users.business.city.CityService;
import autum.com.users.business.city.dto.CityDto;
import autum.com.users.infrastructure.mapstruct.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityControllerTest {

    @Test
    public void findCity() {
        var name = "London";
        var pageable = PageRequest.of(0, 10);

        var cityService = Mockito.mock(CityService.class);
        var mapper = Mockito.mock(Mapper.class);

        var cityDto = new CityDto();
        cityDto.setName("London");
        cityDto.setCountry("UK");
        cityDto.setIdentifier("FOTPREWQERTY78965FVCDRT89GHYTU09");
        cityDto.setRegion("Greater London");
        cityDto.setId(1L);

        Mockito.when(cityService.findCityByName(name, pageable))
                .thenReturn(List.of(cityDto));

        var cityResponse = new CityResponse();
        cityResponse.setIdentifier(cityDto.getIdentifier());
        cityResponse.setName(cityDto.getName());
        cityResponse.setCountry(cityDto.getCountry());
        cityResponse.setRegion(cityDto.getRegion());

        Mockito.when(mapper.map(cityDto, CityResponse.class))
                .thenReturn(cityResponse);

        var cityController = new CityController(cityService, mapper);
        var result = cityController.findCity(name, pageable);
        assertEquals(List.of(cityResponse), result);

        Mockito.verify(cityService, Mockito.times(1))
                .findCityByName(name, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(cityDto, CityResponse.class);
    }
}