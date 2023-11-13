package business;

import autum.com.users.business.city.CityService;
import autum.com.users.business.city.CityServiceImpl;
import autum.com.users.business.city.dto.CityDto;
import autum.com.users.business.city.exception.CityNotFountException;
import autum.com.users.business.city.repo.CityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityServiceTest {

    @Test
    public void findCityByNameTest() {

        var cityRepository = Mockito.mock(CityRepository.class);
        CityService cityService = new CityServiceImpl(cityRepository);
        var name = "London";
        var pageable = PageRequest.of(0, 10);

        var expectedCity = new CityDto();
        expectedCity.setName("London");
        expectedCity.setCountry("UK");
        expectedCity.setIdentifier("GHFRUT75324CVBN23534JGJDFH756456");
        expectedCity.setRegion("Greater London");
        expectedCity.setId(1L);
        var expectedCities = List.of(expectedCity);

        Mockito.when(cityRepository.findCityByName(name, pageable))
                .thenReturn(expectedCities);

        var result = cityService.findCityByName(name, pageable);
        assertEquals(expectedCities, result);

        Mockito.verify(cityRepository, Mockito.times(1))
                .findCityByName(name, pageable);
    }

    @Test
    public void findCityByNameTest_empty() {
        var cityRepository = Mockito.mock(CityRepository.class);
        CityService cityService = new CityServiceImpl(cityRepository);
        var name = "London";
        var pageable = PageRequest.of(0, 10);

        Mockito.when(cityRepository.findCityByName(name, pageable))
                .thenReturn(List.of());

        var result = cityService.findCityByName(name, pageable);
        assertEquals(List.of(), result);

        Mockito.verify(cityRepository, Mockito.times(1))
                .findCityByName(name, pageable);
    }

    @Test
    public void getCityTest() {
        var cityRepository = Mockito.mock(CityRepository.class);
        CityService cityService = new CityServiceImpl(cityRepository);

        var identifier = "GHFRUT75324CVBN23534JGJDFH756456";

        var expectedCity = new CityDto();
        expectedCity.setName("London");
        expectedCity.setCountry("UK");
        expectedCity.setIdentifier(identifier);
        expectedCity.setRegion("Greater London");
        expectedCity.setId(1L);

        Mockito.when(cityRepository.getCity(identifier))
                .thenReturn(expectedCity);

        var result = cityService.getCity(identifier);
        assertEquals(expectedCity, result);

        Mockito.verify(cityRepository, Mockito.times(1))
                .getCity(identifier);
    }

    @Test
    public void getCityTest_null() {
        var cityRepository = Mockito.mock(CityRepository.class);
        CityService cityService = new CityServiceImpl(cityRepository);

        var identifier = "GHFRUT75324CVBN23534JGJDFH756456";

        Mockito.when(cityRepository.getCity(identifier))
                .thenReturn(null);

        assertThrows(CityNotFountException.class, () -> cityService.getCity(identifier));

        Mockito.verify(cityRepository, Mockito.times(1))
                .getCity(identifier);
    }
}