package persistance;

import autum.com.users.business.city.dto.CityDto;
import autum.com.users.business.city.repo.CityRepository;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistance.city.CityRepositoryImpl;
import autum.com.users.persistance.city.entity.City;
import autum.com.users.persistance.city.repodb.CityRepositoryDb;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

public class CityRepoTest {

    @Test
    public void getCity() {
        var identifier = "1234FGTRIOER5678VBNFJDRT4567OPIR";
        var repo = Mockito.mock(CityRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);
        CityRepository cityRepository = new CityRepositoryImpl(repo, mapper);

        var city = new City();
        city.setId(1L);
        city.setIdentifier(identifier);
        city.setName("London");
        city.setRegion("Greater London");
        city.setCountry("UK");

        Mockito.when(repo.findByIdentifier(identifier))
                .thenReturn(city);

        var cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setIdentifier(identifier);
        cityDto.setCountry(city.getCountry());
        cityDto.setName(city.getName());
        cityDto.setRegion(city.getRegion());

        Mockito.when(mapper.map(city, CityDto.class))
                .thenReturn(cityDto);

        var result = cityRepository.getCity(identifier);
        assertEquals(cityDto, result);

        Mockito.verify(repo, Mockito.times(1))
                .findByIdentifier(identifier);
        Mockito.verify(mapper, Mockito.times(1))
                .map(city, CityDto.class);
    }

    @Test
    public void getCity_null() {
        var identifier = "1234FGTRIOER5678VBNFJDRT4567OPIR";
        var repo = Mockito.mock(CityRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);
        CityRepository cityRepository = new CityRepositoryImpl(repo, mapper);

        Mockito.when(repo.findByIdentifier(identifier))
                .thenReturn(null);
        Mockito.when(mapper.map(null, CityDto.class))
                .thenReturn(null);

        var result = cityRepository.getCity(identifier);
        assertNull(result);

        Mockito.verify(repo, Mockito.times(1))
                .findByIdentifier(identifier);
        Mockito.verify(mapper, Mockito.times(1))
                .map(null, CityDto.class);
    }

    @Test
    public void findCityByName() {
        var name = "London";
        var pageable = PageRequest.of(0, 10);

        var repo = Mockito.mock(CityRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        CityRepository cityRepository = new CityRepositoryImpl(repo, mapper);

        var city = new City();
        city.setId(1L);
        city.setIdentifier("1234FGTRIOER5678VBNFJDRT4567OPIR");
        city.setName("London");
        city.setRegion("Greater London");
        city.setCountry("UK");

        var pages = new PageImpl<>(List.of(city), pageable, 1);

        Mockito.when(repo.findAllByNameLike(name, pageable))
                .thenReturn(pages);

        var cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setIdentifier(city.getIdentifier());
        cityDto.setCountry(city.getCountry());
        cityDto.setName(city.getName());
        cityDto.setRegion(city.getRegion());

        Mockito.when(mapper.map(city, CityDto.class))
                .thenReturn(cityDto);

        var result = cityRepository.findCityByName(name, pageable);
        assertEquals(List.of(cityDto), result);

        Mockito.verify(repo, Mockito.times(1))
                .findAllByNameLike(name, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(city, CityDto.class);
    }

    @Test
    public void findCityByName_empty() {
        var name = "London";
        var pageable = PageRequest.of(0, 10);

        var repo = Mockito.mock(CityRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        CityRepository cityRepository = new CityRepositoryImpl(repo, mapper);

        var pages = Page.<City>empty();

        Mockito.when(repo.findAllByNameLike(name, pageable))
                .thenReturn(pages);

        var result = cityRepository.findCityByName(name, pageable);
        assertEquals(List.of(), result);

        Mockito.verify(repo, Mockito.times(1))
                .findAllByNameLike(name, pageable);
        Mockito.verify(mapper, Mockito.times(0))
                .map(any(), any());
    }

}