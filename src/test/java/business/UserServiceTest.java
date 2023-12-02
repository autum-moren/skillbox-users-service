package business;

import autum.com.users.business.city.dto.CityDto;
import autum.com.users.business.user.UserService;
import autum.com.users.business.user.UserServiceImpl;
import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.business.user.dto.UpdateUserDto;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.dto.UserListDto;
import autum.com.users.business.user.exception.UserAlreadyExistsException;
import autum.com.users.business.user.exception.UserBlockedException;
import autum.com.users.business.user.exception.UserDeactivatedException;
import autum.com.users.business.user.exception.UserNotFoundException;
import autum.com.users.business.user.persistance.UserRepository;
import autum.com.users.persistence.user.UserRepositoryImpl;
import autum.com.users.utils.DateUtil;
import autum.com.users.utils.Generator;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


public class UserServiceTest {

    @Test
    public void getUser_Active_Test() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";

        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setSkills(
                List.of(UserDto.SkillDto.builder()
                        .id(1L)
                        .identifier("VBFHTUIR324534DDDRTTE234523VRTEE")
                        .name("java")
                        .level(9)
                        .build())
        );
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));
        userDto.setSubscribers(100L);
        userDto.setSubscriptions(99L);

        Mockito.when(userRepo.getUserWithSubsCount(userIdentifier))
                .thenReturn(userDto);

        var result = userService.getUserWithCount(userIdentifier);
        assertEquals(userDto, result);

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserWithSubsCount(userIdentifier);
    }

    @Test
    public void getUser_blocked_Test() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setSkills(
                List.of(UserDto.SkillDto.builder()
                        .id(1L)
                        .identifier("VBFHTUIR324534DDDRTTE234523VRTEE")
                        .name("java")
                        .level(9)
                        .build())
        );
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.BLOCKED);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));
        userDto.setSubscribers(100L);
        userDto.setSubscriptions(99L);

        Mockito.when(userRepo.getUserWithSubsCount(userIdentifier))
                .thenReturn(userDto);

        assertThrows(UserBlockedException.class, () -> userService.getUserWithCount(userIdentifier));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserWithSubsCount(userIdentifier);
    }

    @Test
    public void getUser_deactivated_Test() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setSkills(
                List.of(UserDto.SkillDto.builder()
                        .id(1L)
                        .identifier("VBFHTUIR324534DDDRTTE234523VRTEE")
                        .name("java")
                        .level(9)
                        .build())
        );
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setDeactivatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.DEACTIVATED);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));
        userDto.setSubscribers(100L);
        userDto.setSubscriptions(99L);

        Mockito.when(userRepo.getUserWithSubsCount(userIdentifier))
                .thenReturn(userDto);

        assertThrows(UserDeactivatedException.class, () -> userService.getUserWithCount(userIdentifier));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserWithSubsCount(userIdentifier);
    }

    @Test
    public void getUser_notFound_Test() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);

        Mockito.when(userRepo.getUserWithSubsCount(userIdentifier))
                .thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userService.getUserWithCount(userIdentifier));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserWithSubsCount(userIdentifier);
    }

    @Test
    public void getUserListByName() {
        var name = "Olgerd Ruyan";
        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);
        var pageable = PageRequest.of(0, 10);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var user1 = new UserDto();
        user1.setId(1L);
        user1.setIdentifier("123HTUIR324534DDDRTTE234523VRTEE");
        user1.setFirstName("Olgerd");
        user1.setLastName("Ruyan");
        user1.setMiddleName("Omen");
        user1.setMsisdn("375291467890");
        user1.setEmail("email@gmail.com");
        user1.setCity(city);
        user1.setSkills(
                List.of(UserDto.SkillDto.builder()
                        .id(1L)
                        .identifier("VBFHTUIR324534DDDRTTE234523VRTEE")
                        .name("java")
                        .level(9)
                        .build())
        );
        user1.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        user1.setAbout("wtf");
        user1.setCreatedAt(LocalDateTime.now());
        user1.setSex(UserDto.Sex.MALE);
        user1.setStatus(UserDto.Status.ACTIVE);
        user1.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var expendedResult = UserListDto.builder()
                .users(List.of(user1))
                .totalCount(1L)
                .build();

        Mockito.when(userRepo.getUserListByName(name, pageable))
                .thenReturn(expendedResult);

        var result = userService.getUserListByName(name, pageable);
        assertEquals(expendedResult, result);

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserListByName(name, pageable);
    }

    @Test
    public void getUserListByName_empty() {
        var name = "Olgerd Ruyan";
        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);
        var pageable = PageRequest.of(0, 10);

        var expendedResult = UserListDto.builder()
                .users(List.of())
                .totalCount(0L)
                .build();

        Mockito.when(userRepo.getUserListByName(name, pageable))
                .thenReturn(expendedResult);

        var result = userService.getUserListByName(name, pageable);
        assertEquals(expendedResult, result);

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserListByName(name, pageable);
    }

    //TODO full text search preparation test!!!

    @Test
    public void createUser() {
        var email = "email@gmail.com";
        var userRepo = Mockito.mock(UserRepository.class);

        var time = LocalDateTime.now();
        new MockUp<DateUtil>() {
            @Mock
            public LocalDateTime getLocalDateTimeUTCNow() {
                return time;
            }
        };

        var identifier = "11111111111112222211111111111111";
        new MockUp<Generator>() {
            @Mock
            public String generateIdentifier() {
                return identifier;
            }
        };

        UserService userService = new UserServiceImpl(userRepo);

        Mockito.when(userRepo.existsByEmail(email))
                .thenReturn(false);

        var userDto = new UserDto();
        userDto.setIdentifier(identifier);
        userDto.setFirstName("Anna");
        userDto.setLastName("Karenina");
        userDto.setMiddleName("Arkadievna");
        userDto.setEmail("email@gmail.com");
        userDto.setMsisdn("79097893456");
        userDto.setSex(UserDto.Sex.FEMALE);
        userDto.setBirthdayAt(time);
        userDto.setCreatedAt(time);
        userDto.setStatus(UserDto.Status.ACTIVE);

        var userDtoSaved = new UserDto();
        userDtoSaved.setId(1L);
        userDtoSaved.setIdentifier(identifier);
        userDtoSaved.setFirstName("Anna");
        userDtoSaved.setLastName("Karenina");
        userDtoSaved.setMiddleName("Arkadievna");
        userDtoSaved.setEmail("email@gmail.com");
        userDtoSaved.setMsisdn("79097893456");
        userDtoSaved.setSex(UserDto.Sex.FEMALE);
        userDtoSaved.setBirthdayAt(time);
        userDtoSaved.setCreatedAt(time);
        userDtoSaved.setStatus(UserDto.Status.ACTIVE);

        Mockito.when(userRepo.saveUser(userDto))
                .thenReturn(userDtoSaved);

        var createDto = new CreateUserDto();
        createDto.setFirstName("Anna");
        createDto.setLastName("Karenina");
        createDto.setMiddleName("Arkadievna");
        createDto.setEmail("email@gmail.com");
        createDto.setMsisdn("79097893456");
        createDto.setSex(UserDto.Sex.FEMALE);
        createDto.setBirthdayAt(time);

        userService.createUser(createDto);

        Mockito.verify(userRepo, Mockito.times(1))
                .existsByEmail(email);
        Mockito.verify(userRepo, Mockito.times(1))
                .saveUser(userDto);
    }

    @Test
    public void createUser_alreadyExists() {
        var time = LocalDateTime.now();
        var email = "email@gmail.com";
        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);

        Mockito.when(userRepo.existsByEmail(email))
                .thenReturn(true);

        var createDto = new CreateUserDto();
        createDto.setFirstName("Anna");
        createDto.setLastName("Karenina");
        createDto.setMiddleName("Arkadievna");
        createDto.setEmail("email@gmail.com");
        createDto.setMsisdn("79097893456");
        createDto.setSex(UserDto.Sex.FEMALE);
        createDto.setBirthdayAt(time);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(createDto));

        Mockito.verify(userRepo, Mockito.times(1))
                .existsByEmail(email);
        Mockito.verify(userRepo, Mockito.times(0))
                .saveUser(any());
    }

    @Test
    public void updateUser_full_changes() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepositoryImpl.class);

        var userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userRepo.getUser(userIdentifier))
                .thenReturn(userDto);

        var updateUserDto = new UpdateUserDto();
        updateUserDto.setFirstName("Gerd");
        updateUserDto.setLastName("Ruy");
        updateUserDto.setMiddleName("Om");
        updateUserDto.setAbout("ftw");
        updateUserDto.setSex(UserDto.Sex.UNKNOWN);
        updateUserDto.setBirthdayAt(LocalDateTime.now());

        var updatedUserDto = new UserDto();
        updatedUserDto.setId(userDto.getId());
        updatedUserDto.setIdentifier(userDto.getIdentifier());
        updatedUserDto.setFirstName(updateUserDto.getFirstName());
        updatedUserDto.setLastName(updateUserDto.getLastName());
        updatedUserDto.setMiddleName(updateUserDto.getMiddleName());
        updatedUserDto.setMsisdn(userDto.getMsisdn());
        updatedUserDto.setEmail(userDto.getEmail());
        updatedUserDto.setCity(userDto.getCity());
        updatedUserDto.setAvatarUrl(userDto.getAvatarUrl());
        updatedUserDto.setAbout(updateUserDto.getAbout());
        updatedUserDto.setCreatedAt(userDto.getCreatedAt());
        updatedUserDto.setSex(updateUserDto.getSex());
        updatedUserDto.setStatus(userDto.getStatus());
        updatedUserDto.setBirthdayAt(updateUserDto.getBirthdayAt());

        Mockito.when(userRepo.saveUser(updatedUserDto))
                        .thenReturn(updatedUserDto);

        assertAll(() -> userService.updateUser(updateUserDto, userIdentifier));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUser(userIdentifier);
        Mockito.verify(userRepo, Mockito.times(1))
                .saveUser(any());
    }

    @Test
    public void updateUser_some_changes() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userRepo.getUser(userIdentifier))
                .thenReturn(userDto);

        var updateUserDto = new UpdateUserDto();
        updateUserDto.setAbout("ftw");
        updateUserDto.setSex(UserDto.Sex.UNKNOWN);
        updateUserDto.setBirthdayAt(LocalDateTime.now());

        var updatedUserDto = new UserDto();
        updatedUserDto.setId(userDto.getId());
        updatedUserDto.setIdentifier(userDto.getIdentifier());
        updatedUserDto.setFirstName(userDto.getFirstName());
        updatedUserDto.setLastName(userDto.getLastName());
        updatedUserDto.setMiddleName(userDto.getMiddleName());
        updatedUserDto.setMsisdn(userDto.getMsisdn());
        updatedUserDto.setEmail(userDto.getEmail());
        updatedUserDto.setCity(userDto.getCity());
        updatedUserDto.setAvatarUrl(userDto.getAvatarUrl());
        updatedUserDto.setAbout(updateUserDto.getAbout());
        updatedUserDto.setCreatedAt(userDto.getCreatedAt());
        updatedUserDto.setSex(updateUserDto.getSex());
        updatedUserDto.setStatus(UserDto.Status.ACTIVE);
        updatedUserDto.setBirthdayAt(updateUserDto.getBirthdayAt());

        Mockito.when(userRepo.saveUser(updatedUserDto))
                .thenReturn(updatedUserDto);

        userService.updateUser(updateUserDto, userIdentifier);

        Mockito.verify(userRepo, Mockito.times(1))
                .getUser(userIdentifier);
        Mockito.verify(userRepo, Mockito.times(1))
                .saveUser(updatedUserDto);
    }

    @Test
    public void deactivateUser() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var time = LocalDateTime.now();
        new MockUp<DateUtil>() {
            @Mock
            public LocalDateTime getLocalDateTimeUTCNow() {
                return time;
            }
        };

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userRepo.getUser(userIdentifier))
                .thenReturn(userDto);

        var userDtoEdited = new UserDto();
        userDtoEdited.setId(1L);
        userDtoEdited.setIdentifier(userIdentifier);
        userDtoEdited.setFirstName("Olgerd");
        userDtoEdited.setLastName("Ruyan");
        userDtoEdited.setMiddleName("Omen");
        userDtoEdited.setMsisdn("375291467890");
        userDtoEdited.setEmail("email@gmail.com");
        userDtoEdited.setCity(city);
        userDtoEdited.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDtoEdited.setAbout("wtf");
        userDtoEdited.setCreatedAt(time);
        userDtoEdited.setSex(UserDto.Sex.MALE);
        userDtoEdited.setStatus(UserDto.Status.DEACTIVATED);
        userDtoEdited.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));
        userDtoEdited.setDeactivatedAt(time);

        Mockito.when(userRepo.saveUser(userDtoEdited))
                .thenReturn(userDtoEdited);

        userService.deactivateUser(userIdentifier);

        Mockito.verify(userRepo, Mockito.times(1))
                .saveUser(userDtoEdited);
        Mockito.verify(userRepo, Mockito.times(1))
                .getUser(userIdentifier);
    }

    @Test
    public void deactivateUser_blocked() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.BLOCKED);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userRepo.getUser(userIdentifier))
                .thenReturn(userDto);

        assertThrows(UserBlockedException.class, () -> userService.deactivateUser(userIdentifier));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUser(userIdentifier);
    }

    @Test
    public void deactivateUser_deactivated() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(userIdentifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.DEACTIVATED);
        userDto.setDeactivatedAt(LocalDateTime.now());
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        Mockito.when(userRepo.getUser(userIdentifier))
                .thenReturn(userDto);

        assertThrows(UserDeactivatedException.class, () -> userService.deactivateUser(userIdentifier));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUser(userIdentifier);
    }

    @Test
    public void deactivateUser_null() {
        var userIdentifier = "123HTUIR324534DDDRTTE234523VRTEE";
        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);

        Mockito.when(userRepo.getUser(userIdentifier))
                .thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userService.deactivateUser(userIdentifier));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUser(userIdentifier);
    }

    @Test
    public void getActiveUserList() {
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("123HTUIR324534DDDRTTE234523VRTEE");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setIdentifier("GUTIREDFVCX546785FGHYR56744FRTEW");
        userDto1.setFirstName("Olgerd");
        userDto1.setLastName("Ruyan");
        userDto1.setMiddleName("Omen");
        userDto1.setMsisdn("375291467890");
        userDto1.setEmail("email@gmail.com");
        userDto1.setCity(city);
        userDto1.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto1.setAbout("wtf");
        userDto1.setCreatedAt(LocalDateTime.now());
        userDto1.setSex(UserDto.Sex.MALE);
        userDto1.setStatus(UserDto.Status.ACTIVE);
        userDto1.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var identifiers = Set.of("123HTUIR324534DDDRTTE234523VRTEE", "GUTIREDFVCX546785FGHYR56744FRTEW");

        Mockito.when(userRepo.getUserListByIdentifier(identifiers))
                .thenReturn(List.of(userDto, userDto1));

        var result = userService.getActiveUserList(identifiers);
        assertEquals(List.of(userDto, userDto1), result);

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserListByIdentifier(identifiers);
    }

    /**
     * Один из users деактивирован.
     */
    @Test
    public void getActiveUserList_one_deactivated() {
        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("123HTUIR324534DDDRTTE234523VRTEE");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setIdentifier("GUTIREDFVCX546785FGHYR56744FRTEW");
        userDto1.setFirstName("Olgerd");
        userDto1.setLastName("Ruyan");
        userDto1.setMiddleName("Omen");
        userDto1.setMsisdn("375291467890");
        userDto1.setEmail("email@gmail.com");
        userDto1.setCity(city);
        userDto1.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto1.setAbout("wtf");
        userDto1.setCreatedAt(LocalDateTime.now());
        userDto1.setSex(UserDto.Sex.MALE);
        userDto1.setStatus(UserDto.Status.DEACTIVATED);
        userDto1.setDeactivatedAt(LocalDateTime.now());
        userDto1.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var identifiers = Set.of("123HTUIR324534DDDRTTE234523VRTEE", "GUTIREDFVCX546785FGHYR56744FRTEW");

        Mockito.when(userRepo.getUserListByIdentifier(identifiers))
                .thenReturn(List.of(userDto, userDto1));

        assertThrows(UserDeactivatedException.class, () -> userService.getActiveUserList(identifiers));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserListByIdentifier(identifiers);
    }

    /**
     * Один из users заблокирован.
     */
    @Test
    public void getActiveUserList_one_blocked() {
        var userRepo = Mockito.mock(UserRepository.class);
        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("123HTUIR324534DDDRTTE234523VRTEE");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setIdentifier("GUTIREDFVCX546785FGHYR56744FRTEW");
        userDto1.setFirstName("Olgerd");
        userDto1.setLastName("Ruyan");
        userDto1.setMiddleName("Omen");
        userDto1.setMsisdn("375291467890");
        userDto1.setEmail("email@gmail.com");
        userDto1.setCity(city);
        userDto1.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto1.setAbout("wtf");
        userDto1.setCreatedAt(LocalDateTime.now());
        userDto1.setSex(UserDto.Sex.MALE);
        userDto1.setStatus(UserDto.Status.BLOCKED);
        userDto1.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var identifiers = Set.of("123HTUIR324534DDDRTTE234523VRTEE", "GUTIREDFVCX546785FGHYR56744FRTEW");

        Mockito.when(userRepo.getUserListByIdentifier(identifiers))
                .thenReturn(List.of(userDto, userDto1));

        assertThrows(UserBlockedException.class, () -> userService.getActiveUserList(identifiers));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserListByIdentifier(identifiers);
    }

    /**
     * Найден 1 из двух.
     */
    @Test
    public void getActiveUserList_return_one() {
        var userRepo = Mockito.mock(UserRepository.class);

        UserService userService = new UserServiceImpl(userRepo);

        var city = new CityDto();
        city.setName("London");
        city.setCountry("UK");
        city.setIdentifier("VBFRTEYSDFGDD23423FDSDFD5647DFFD");
        city.setRegion("Greater London");
        city.setId(1L);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("123HTUIR324534DDDRTTE234523VRTEE");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ruyan");
        userDto.setMiddleName("Omen");
        userDto.setMsisdn("375291467890");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(city);
        userDto.setAvatarUrl("http://avatars.com/avatar_1.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(LocalDateTime.of(1993, 12, 30, 4, 0));

        var identifiers = Set.of("123HTUIR324534DDDRTTE234523VRTEE", "GUTIREDFVCX546785FGHYR56744FRTEW");

        Mockito.when(userRepo.getUserListByIdentifier(identifiers))
                .thenReturn(List.of(userDto));

        assertThrows(UserNotFoundException.class, () -> userService.getActiveUserList(identifiers));

        Mockito.verify(userRepo, Mockito.times(1))
                .getUserListByIdentifier(identifiers);
    }
}