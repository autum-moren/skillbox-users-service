package autum.com.users.api;

import autum.com.users.api.response.CityResponse;
import autum.com.users.business.city.CityService;
import autum.com.users.infrastructure.mapstruct.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/city")
public class CityController {

    private final CityService cityService;
    private final Mapper mapper;

    @GetMapping("/{name}")
    public List<CityResponse> findCity(@PathVariable String name, Pageable pageable) {
        var list = cityService.findCityByName(name, pageable);
        return list.stream()
                .map(city -> mapper.map(city, CityResponse.class))
                .collect(Collectors.toList());
    }

    //TODO setCityToUser
}