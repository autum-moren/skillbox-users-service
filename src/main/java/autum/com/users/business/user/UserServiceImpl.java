package autum.com.users.business.user;

import autum.com.users.business.user.dto.UserDto;
import autum.com.users.business.user.exception.UserBlockedException;
import autum.com.users.business.user.exception.UserNotFoundException;
import autum.com.users.business.user.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import static autum.com.users.business.user.dto.UserDto.Status.BLOCKED;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    public UserDto getUser(String identifier) {
        var user = userRepo.getUser(identifier);
        checkUser(user);
        return user;
    }

    public void checkUser(UserDto user) {
        if (user == null) {
            throw new UserNotFoundException();
        } else if (user.getStatus() == BLOCKED) {
            throw new UserBlockedException();
        }
    }
}