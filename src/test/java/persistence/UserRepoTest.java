package persistence;

import autum.com.users.business.city.dto.CityDto;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.dto.UserListDto;
import autum.com.users.business.user.persistance.UserRepository;
import autum.com.users.infrastructure.mapstruct.Mapper;
import autum.com.users.persistence.city.entity.City;
import autum.com.users.persistence.user.UserRepositoryImpl;
import autum.com.users.persistence.user.entity.Skill;
import autum.com.users.persistence.user.entity.User;
import autum.com.users.persistence.user.repodb.UserRepositoryDb;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepoTest {

    @Test
    public void getUserWithSubsCount() {
        var identifier = "RTYIO567FDDER235BNJH34353FDERRFT";
        var repo = Mockito.mock(UserRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var city = new City();
        city.setId(1L);
        city.setIdentifier("1234FGTRIOER5678VBNFJDRT4567OPIR");
        city.setName("London");
        city.setRegion("Greater London");
        city.setCountry("UK");

        var skill = new Skill();
        skill.setId(1L);
        skill.setIdentifier("RTYUIGFHRT23445GGFF23DDFGD353453");
        skill.setName("Java");
        skill.setLevel(9);

        var user = new User();
        user.setId(1L);
        user.setIdentifier(identifier);
        user.setFirstName("Olgerd");
        user.setLastName("Ryan");
        user.setMiddleName("Oly");
        user.setMsisdn("375291009911");
        user.setEmail("email@gmail.com");
        user.setCity(city);
        user.setSkills(List.of(skill));
        user.setAvatarUrl("https://uprostim.com/image107-65.jpg");
        user.setAbout("wtf");
        user.setCreatedAt(time);
        user.setSex(1);
        user.setStatus(1);
        user.setBirthdayAt(time);
        user.setSubscribers(10L);
        user.setSubscriptions(20L);

        Mockito.when(repo.findByIdentifierWithSubscrCount(identifier))
                .thenReturn(user);

        var cityDto = new CityDto();
        cityDto.setId(1L);
        cityDto.setIdentifier("1234FGTRIOER5678VBNFJDRT4567OPIR");
        cityDto.setName("London");
        cityDto.setRegion("Greater London");
        cityDto.setCountry("UK");

        var skillDto = UserDto.SkillDto.builder()
                .id(1L)
                .identifier("RTYUIGFHRT23445GGFF23DDFGD353453")
                .name("Java")
                .level(9)
                .build();

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(identifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ryan");
        userDto.setMiddleName("Oly");
        userDto.setMsisdn("375291009911");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(cityDto);
        userDto.setSkills(List.of(skillDto));
        userDto.setAvatarUrl("https://uprostim.com/image107-65.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(time);
        userDto.setSubscribers(10L);
        userDto.setSubscriptions(20L);

        Mockito.when(mapper.map(user, UserDto.class))
                .thenReturn(userDto);

        UserRepository userRepository = new UserRepositoryImpl(repo, mapper);

        var result = userRepository.getUserWithSubsCount(identifier);
        assertEquals(userDto, result);

        Mockito.verify(repo, Mockito.times(1))
                .findByIdentifierWithSubscrCount(identifier);
        Mockito.verify(mapper, Mockito.times(1))
                .map(user, UserDto.class);
    }

    @Test
    public void getUser() {
        var identifier = "RTYIO567FDDER235BNJH34353FDERRFT";
        var repo = Mockito.mock(UserRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var city = new City();
        city.setId(1L);
        city.setIdentifier("1234FGTRIOER5678VBNFJDRT4567OPIR");
        city.setName("London");
        city.setRegion("Greater London");
        city.setCountry("UK");

        var skill = new Skill();
        skill.setId(1L);
        skill.setIdentifier("RTYUIGFHRT23445GGFF23DDFGD353453");
        skill.setName("Java");
        skill.setLevel(9);

        var user = new User();
        user.setId(1L);
        user.setIdentifier(identifier);
        user.setFirstName("Olgerd");
        user.setLastName("Ryan");
        user.setMiddleName("Oly");
        user.setMsisdn("375291009911");
        user.setEmail("email@gmail.com");
        user.setCity(city);
        user.setSkills(List.of(skill));
        user.setAvatarUrl("https://uprostim.com/image107-65.jpg");
        user.setAbout("wtf");
        user.setCreatedAt(time);
        user.setSex(1);
        user.setStatus(1);
        user.setBirthdayAt(time);

        Mockito.when(repo.findByIdentifier(identifier))
                .thenReturn(user);

        var cityDto = new CityDto();
        cityDto.setId(1L);
        cityDto.setIdentifier("1234FGTRIOER5678VBNFJDRT4567OPIR");
        cityDto.setName("London");
        cityDto.setRegion("Greater London");
        cityDto.setCountry("UK");

        var skillDto = UserDto.SkillDto.builder()
                .id(1L)
                .identifier("RTYUIGFHRT23445GGFF23DDFGD353453")
                .name("Java")
                .level(9)
                .build();

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier(identifier);
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ryan");
        userDto.setMiddleName("Oly");
        userDto.setMsisdn("375291009911");
        userDto.setEmail("email@gmail.com");
        userDto.setCity(cityDto);
        userDto.setSkills(List.of(skillDto));
        userDto.setAvatarUrl("https://uprostim.com/image107-65.jpg");
        userDto.setAbout("wtf");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(time);

        Mockito.when(mapper.map(user, UserDto.class))
                .thenReturn(userDto);

        UserRepository userRepository = new UserRepositoryImpl(repo, mapper);

        var result = userRepository.getUser(identifier);
        assertEquals(userDto, result);

        Mockito.verify(repo, Mockito.times(1))
                .findByIdentifier(identifier);
        Mockito.verify(mapper, Mockito.times(1))
                .map(user, UserDto.class);
    }

    @Test
    public void getUserListByName() {
        var name = "Olgerd Ryan";
        var pageable = PageRequest.of(0, 10);
        var repo = Mockito.mock(UserRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var user = new User();
        user.setId(1L);
        user.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        user.setFirstName("Olgerd");
        user.setLastName("Ryan");
        user.setMiddleName("Oly");
        user.setEmail("email@gmail.com");
        user.setCreatedAt(time);
        user.setSex(1);
        user.setStatus(1);
        user.setBirthdayAt(time);

        var page = new PageImpl<>(List.of(user), pageable, 1);

        var fullTextSearch = "'Olgerd':*&'Ryan':*";

        Mockito.when(repo.findByName(fullTextSearch, pageable))
                .thenReturn(page);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ryan");
        userDto.setMiddleName("Oly");
        userDto.setEmail("email@gmail.com");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(time);

        Mockito.when(mapper.map(user, UserDto.class))
                .thenReturn(userDto);

        UserRepository userRepository = new UserRepositoryImpl(repo, mapper);

        var result = userRepository.getUserListByName(name, pageable);

        var extended = UserListDto.builder()
                .totalCount(1)
                .users(List.of(userDto))
                .build();
        assertEquals(extended, result);

        Mockito.verify(repo, Mockito.times(1))
                .findByName(fullTextSearch, pageable);
        Mockito.verify(mapper, Mockito.times(1))
                .map(user, UserDto.class);
    }

    @Test
    public void existsByEmail_true() {
        var email = "email@gmail.com";
        var repo = Mockito.mock(UserRepositoryDb.class);

        Mockito.when(repo.existsByEmail(email))
                .thenReturn(true);

        UserRepository userRepository = new UserRepositoryImpl(repo, null);

        var result = userRepository.existsByEmail(email);
        assertTrue(result);

        Mockito.verify(repo, Mockito.times(1))
                .existsByEmail(email);
    }

    @Test
    public void existsByEmail_false() {
        var email = "email@gmail.com";
        var repo = Mockito.mock(UserRepositoryDb.class);

        Mockito.when(repo.existsByEmail(email))
                .thenReturn(false);

        UserRepository userRepository = new UserRepositoryImpl(repo, null);

        var result = userRepository.existsByEmail(email);
        assertFalse(result);

        Mockito.verify(repo, Mockito.times(1))
                .existsByEmail(email);
    }

    @Test
    public void saveUser() {
        var repo = Mockito.mock(UserRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var userDto = new UserDto();
        userDto.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ryan");
        userDto.setMiddleName("Oly");
        userDto.setEmail("email@gmail.com");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(time);

        var user = new User();
        user.setId(1L);
        user.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        user.setFirstName("Olgerd");
        user.setLastName("Ryan");
        user.setMiddleName("Oly");
        user.setEmail("email@gmail.com");
        user.setCreatedAt(time);
        user.setSex(1);
        user.setStatus(1);
        user.setBirthdayAt(time);

        var savedUser = new User();
        user.setId(1L);
        user.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        user.setFirstName("Olgerd");
        user.setLastName("Ryan");
        user.setMiddleName("Oly");
        user.setEmail("email@gmail.com");
        user.setCreatedAt(time);
        user.setSex(1);
        user.setStatus(1);
        user.setBirthdayAt(time);

        var savedUserDto = new UserDto();
        savedUserDto.setId(1L);
        savedUserDto.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        savedUserDto.setFirstName("Olgerd");
        savedUserDto.setLastName("Ryan");
        savedUserDto.setMiddleName("Oly");
        savedUserDto.setEmail("email@gmail.com");
        savedUserDto.setCreatedAt(time);
        savedUserDto.setSex(UserDto.Sex.MALE);
        savedUserDto.setStatus(UserDto.Status.ACTIVE);
        savedUserDto.setBirthdayAt(time);

        Mockito.when(repo.save(user))
                .thenReturn(savedUser);

        Mockito.when(mapper.map(userDto, User.class))
                .thenReturn(user);
        Mockito.when(mapper.map(savedUser, UserDto.class))
                .thenReturn(savedUserDto);

        UserRepository userRepository = new UserRepositoryImpl(repo, mapper);

        var result = userRepository.saveUser(userDto);
        assertEquals(savedUserDto, result);

        Mockito.verify(repo, Mockito.times(1))
                .save(user);
        Mockito.verify(mapper, Mockito.times(1))
                .map(userDto, User.class);
        Mockito.verify(mapper, Mockito.times(1))
                .map(savedUser, UserDto.class);

    }

    @Test
    public void getUserListByIdentifier() {
        var identifiers = Set.of("RTYIGFDR875694CVGFRT897RTYPO89UI","111IGFDR875614CVGFRT897RTYPO89U1");
        var repo = Mockito.mock(UserRepositoryDb.class);
        var mapper = Mockito.mock(Mapper.class);

        var time = LocalDateTime.now();

        var user = new User();
        user.setId(1L);
        user.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        user.setFirstName("Olgerd");
        user.setLastName("Ryan");
        user.setMiddleName("Oly");
        user.setEmail("email@gmail.com");
        user.setCreatedAt(time);
        user.setSex(1);
        user.setStatus(1);
        user.setBirthdayAt(time);

        var user1 = new User();
        user1.setId(2L);
        user1.setIdentifier("111RTUYIOFDER45678VBGF56FGTR4567");
        user1.setFirstName("Olg");
        user1.setLastName("R");
        user1.setMiddleName("Ol");
        user1.setEmail("email123@gmail.com");
        user1.setCreatedAt(time);
        user1.setSex(1);
        user1.setStatus(1);
        user1.setBirthdayAt(time);

        var userList = List.of(user, user1);

        Mockito.when(repo.findAllByIdentifierIn(identifiers))
                .thenReturn(userList);

        var userDto = new UserDto();
        userDto.setId(1L);
        userDto.setIdentifier("DFGRTUYIOFDER45678VBGF56FGTR4567");
        userDto.setFirstName("Olgerd");
        userDto.setLastName("Ryan");
        userDto.setMiddleName("Oly");
        userDto.setEmail("email@gmail.com");
        userDto.setCreatedAt(time);
        userDto.setSex(UserDto.Sex.MALE);
        userDto.setStatus(UserDto.Status.ACTIVE);
        userDto.setBirthdayAt(time);

        var user1Dto = new UserDto();
        user1Dto.setId(2L);
        user1Dto.setIdentifier("111RTUYIOFDER45678VBGF56FGTR4567");
        user1Dto.setFirstName("Olg");
        user1Dto.setLastName("R");
        user1Dto.setMiddleName("Ol");
        user1Dto.setEmail("email123@gmail.com");
        user1Dto.setCreatedAt(time);
        user1Dto.setSex(UserDto.Sex.MALE);
        user1Dto.setStatus(UserDto.Status.ACTIVE);
        user1Dto.setBirthdayAt(time);

        Mockito.when(mapper.map(user, UserDto.class))
                .thenReturn(userDto);
        Mockito.when(mapper.map(user1, UserDto.class))
                .thenReturn(user1Dto);

        UserRepository userRepository = new UserRepositoryImpl(repo, mapper);
        var result = userRepository.getUserListByIdentifier(identifiers);
        assertEquals(List.of(userDto, user1Dto), result);

        Mockito.verify(repo, Mockito.times(1))
                .findAllByIdentifierIn(identifiers);
        Mockito.verify(mapper, Mockito.times(1))
                .map(user, UserDto.class);
        Mockito.verify(mapper, Mockito.times(1))
                .map(user1, UserDto.class);
    }
}