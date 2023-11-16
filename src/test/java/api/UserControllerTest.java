package api;

import autum.com.users.api.UserController;
import autum.com.users.api.request.CreateUserRequest;
import autum.com.users.api.request.UpdateUserRequest;
import autum.com.users.api.response.ShortUserResponse;
import autum.com.users.api.response.UserListResponse;
import autum.com.users.api.response.UserResponse;
import autum.com.users.business.city.dto.CityDto;
import autum.com.users.business.user.UserService;
import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.business.user.dto.UpdateUserDto;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.dto.UserListDto;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

public class UserControllerTest {

    @Test
    public void create() {
        var userService = Mockito.mock(UserService.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = System.currentTimeMillis();

        var userController = new UserController(userService, mapper);

        var request = new CreateUserRequest();
        request.setFirstName("Olgerd");
        request.setLastName("Ryan");
        request.setMiddleName("Oly");
        request.setMsisdn("375334560978");
        request.setEmail("email@gmail.com");
        request.setSex("MALE");
        request.setBirthdayAt(time);

        var userDto = new CreateUserDto();
        userDto.setFirstName(request.getFirstName());
        userDto.setLastName(request.getLastName());
        userDto.setMiddleName(request.getMiddleName());
        userDto.setMsisdn(request.getMsisdn());
        userDto.setEmail(request.getEmail());
        userDto.setSex(UserDto.Sex.valueOf(request.getSex()));
        userDto.setBirthdayAt(DateUtil.getLocalDateTimeUTC(request.getBirthdayAt()));

        Mockito.when(mapper.map(request, CreateUserDto.class))
                .thenReturn(userDto);

        doNothing().when(userService)
                .createUser(userDto);

        userController.create(request);

        Mockito.verify(userService, Mockito.times(1))
                .createUser(userDto);
        Mockito.verify(mapper, Mockito.times(1))
                .map(request, CreateUserDto.class);
    }

    @Test
    public void get() {
        var identifier = "FGTIRETOPTUIRE5678VBFD456FGTR984";

        var time = LocalDateTime.now();

        var userService = Mockito.mock(UserService.class);
        var mapper = Mockito.mock(Mapper.class);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("FOTPREWQERTY78965FVCDRT89GHYTU09");
        city.setRegion("Greater London");
        city.setId(1L);

        var skill = UserDto.SkillDto.builder()
                .id(1L)
                .identifier("TYIREOTRYF45678VBFGT657FGTRIOE45")
                .name("java")
                .level(9)
                .build();

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(identifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ryan");
        userDto.setMiddleName("Oly");
        userDto.setMsisdn("375295567700");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setSkills(List.of(skill));
        userDto.setAvatarUrl("https://android-obzor.com/man-silhouette.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(time);
        userDto.setSubscriptions(100L);
        userDto.setSubscribers(99L);

        Mockito.when(userService.getUser(identifier))
                .thenReturn(userDto);

        var cityResponse = new UserResponse.City();
        cityResponse.setName(city.getName());
        cityResponse.setCountry(city.getCountry());
        cityResponse.setIdentifier(city.getIdentifier());
        cityResponse.setRegion(city.getRegion());

        var skillsResponse = new UserResponse.Skill();
        skillsResponse.setIdentifier(skill.getIdentifier());
        skillsResponse.setName(skill.getName());
        skillsResponse.setLevel(skill.getLevel());

        var userResponse = new UserResponse();
        userResponse.setIdentifier(userDto.getIdentifier());
        userResponse.setFirstName(userDto.getFirstName());
        userResponse.setLastName(userDto.getLastName());
        userResponse.setMiddleName(userDto.getMiddleName());
        userResponse.setMsisdn(userDto.getMsisdn());
        userResponse.setEmail(userDto.getEmail());
        userResponse.setCity(cityResponse);
        userResponse.setSkills(List.of(skillsResponse));
        userResponse.setAvatarUrl(userDto.getAvatarUrl());
        userResponse.setAbout(userDto.getAbout());
        userResponse.setCreatedAt(DateUtil.getMillisFromLocalDateTime(userDto.getBirthdayAt()));
        userResponse.setStatus(userDto.getStatus().name());
        userResponse.setSex(userDto.getSex().name());
        userResponse.setBirthdayAt(DateUtil.getMillisFromLocalDateTime(userDto.getBirthdayAt()));
        userResponse.setSubscriptions(userDto.getSubscriptions());
        userResponse.setSubscribers(userDto.getSubscribers());

        Mockito.when(mapper.map(userDto, UserResponse.class))
                .thenReturn(userResponse);

        var userController = new UserController(userService, mapper);
        var result = userController.get(identifier);
        assertEquals(userResponse, result);

        Mockito.verify(userService, Mockito.times(1))
                .getUser(identifier);
        Mockito.verify(mapper, Mockito.times(1))
                .map(userDto, UserResponse.class);
    }

    @Test
    public void update() {
        var identifier = "FGTIRETOPTUIRE5678VBFD456FGTR984";

        var time = System.currentTimeMillis();

        var userService = Mockito.mock(UserService.class);
        var mapper = Mockito.mock(Mapper.class);

        var request = new UpdateUserRequest();
        request.setFirstName("Onyx");
        request.setLastName("Lin");
        request.setMiddleName("Yu");
        request.setAbout("wtf");
        request.setSex("MALE");
        request.setBirthdayAt(time);

        var requestDto = new UpdateUserDto();
        requestDto.setFirstName(request.getFirstName());
        requestDto.setLastName(request.getLastName());
        requestDto.setMiddleName(request.getMiddleName());
        requestDto.setAbout(request.getAbout());
        requestDto.setSex(UserDto.Sex.valueOf(request.getSex()));
        requestDto.setBirthdayAt(DateUtil.getLocalDateTimeUTC(request.getBirthdayAt()));

        Mockito.when(mapper.map(request, UpdateUserDto.class))
                .thenReturn(requestDto);

        doNothing().when(userService)
                .updateUser(requestDto, identifier);

        var userController = new UserController(userService, mapper);
        userController.update(request, identifier);

        Mockito.verify(userService, Mockito.times(1))
                .updateUser(requestDto, identifier);
        Mockito.verify(mapper, Mockito.times(1))
                .map(request, UpdateUserDto.class);
    }

    @Test
    public void delete() {
        var identifier = "FGTIRETOPTUIRE5678VBFD456FGTR984";

        var userService = Mockito.mock(UserService.class);

        var userController = new UserController(userService, null);
        userController.delete(identifier);

        Mockito.verify(userService, Mockito.times(1))
                .deactivateUser(identifier);
    }

    @Test
    public void getList() {
        var name = "Olgerd";
        var pageable = PageRequest.of(0, 10);

        var userService = Mockito.mock(UserService.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("FOTPREWQERTY78965FVCDRT89GHYTU09");
        city.setRegion("Greater London");
        city.setId(1L);

        var skill = UserDto.SkillDto.builder()
                .id(1L)
                .identifier("TYIREOTRYF45678VBFGT657FGTRIOE45")
                .name("java")
                .level(9)
                .build();

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("GFYRTIYERTY678954VBGFR567GFRTE56");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ryan");
        userDto.setMiddleName("Oly");
        userDto.setMsisdn("375295567700");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setSkills(List.of(skill));
        userDto.setAvatarUrl("https://android-obzor.com/man-silhouette.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(time);
        userDto.setSubscriptions(100L);
        userDto.setSubscribers(99L);

        var userListDto = UserListDto.builder()
                .totalCount(1L)
                .users(List.of(userDto))
                .build();

        Mockito.when(userService.getUserListByName(name, pageable))
                .thenReturn(userListDto);

        var userShort = new ShortUserResponse();
        userShort.setIdentifier(userDto.getIdentifier());
        userShort.setFirstName(userDto.getFirstName());
        userShort.setLastName(userDto.getLastName());
        userShort.setAvatarUrl(userDto.getAvatarUrl());

        var userListResponse = new UserListResponse();
        userListResponse.setTotalCount(userListDto.getTotalCount());
        userListResponse.setUsers(List.of(userShort));

        Mockito.when(mapper.map(userListDto, UserListResponse.class))
                .thenReturn(userListResponse);

        var userController = new UserController(userService, mapper);
        var result = userController.getUserList(name, pageable);
        assertEquals(userListResponse, result);

        Mockito.verify(userService, Mockito.times(1))
                .getUserListByName(name, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(userListDto, UserListResponse.class);
    }

}