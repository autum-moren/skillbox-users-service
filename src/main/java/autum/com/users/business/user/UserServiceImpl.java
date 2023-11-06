package autum.com.users.business.user;

import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.business.user.dto.UpdateUserDto;
import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.exception.UserAlreadyExistsException;
import autum.com.users.business.user.exception.UserBlockedException;
import autum.com.users.business.user.exception.UserDeactivatedException;
import autum.com.users.business.user.exception.UserNotFoundException;
import autum.com.users.business.user.persistance.UserRepository;
import autum.com.users.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

import static autum.com.users.business.user.dto.UserDto.Status.BLOCKED;
import static autum.com.users.business.user.dto.UserDto.Status.DEACTIVATED;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    public UserDto getUser(String identifier) {
        var user = userRepo.getUserWithSubsCount(identifier);
        checkUser(user);
        return user;
    }

    @Override
    public void createUser(CreateUserDto dto) {
        if (!userRepo.existsByEmail(dto.getEmail())) {
            var user = new UserDto(dto);
            userRepo.saveUser(user);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public void updateUser(UpdateUserDto dto, String identifier) {
        var user = userRepo.getUser(identifier);
        checkUser(user);
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getMiddleName() != null) {
            user.setMiddleName(dto.getMiddleName());
        }
        if (dto.getAbout() != null) {
            user.setAbout(dto.getAbout());
        }
        if (dto.getBirthdayAt() != null) {
            user.setBirthdayAt(dto.getBirthdayAt());
        }
        if (dto.getSex() != null) {
            user.setSex(dto.getSex());
        }
        userRepo.saveUser(user);
    }

    @Override
    public void deactivateUser(String identifier) {
        var user = userRepo.getUser(identifier);
        checkUser(user);
        user.setStatus(DEACTIVATED);
        user.setDeactivatedAt(DateUtil.getLocalDateTimeUTCNow());
        userRepo.saveUser(user);
    }

    @Override
    public List<UserDto> getActiveUserList(Set<String> identifiers) {
        var list = userRepo.getUserListByIdentifier(identifiers);
        if (list.size() < identifiers.size()) {
            throw new UserNotFoundException();
        }
        for (UserDto user : list) {
            checkUser(user);
        }
        return list;
    }

    public void checkUser(UserDto user) {
        if (user == null) {
            throw new UserNotFoundException();
        } else if (user.getStatus() == BLOCKED) {
            throw new UserBlockedException(user.getFirstName(), user.getLastName());
        } else if (user.getStatus() == DEACTIVATED) {
            throw new UserDeactivatedException(user.getFirstName(), user.getLastName());
        }
    }
}