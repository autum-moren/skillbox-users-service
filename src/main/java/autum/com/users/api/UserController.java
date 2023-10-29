package autum.com.users.api;

import autum.com.users.api.request.CreateUserRequest;
import autum.com.users.api.request.UpdateUserRequest;
import autum.com.users.api.response.UserResponse;
import autum.com.users.business.user.UserService;
import autum.com.users.infrastructure.mapstruct.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

//    @PostMapping("/create")
//    @ResponseStatus(code = OK)
//    public void create(@RequestBody CreateUserRequest request) {
//        return "Hello world from users service!";
//    }

    @GetMapping(value = "/{identifier}")
    @ResponseBody
    public UserResponse get(@PathVariable String identifier) {
        var user = userService.getUser(identifier);
        return mapper.map(user, UserResponse.class);
    }

//    @PostMapping("/update")
//    @ResponseStatus(code = OK)
//    public void update(@RequestBody UpdateUserRequest request) {
//        return "Hello world from users service!";
//    }
//
//    @PostMapping("/{identifier}/delete")
//    @ResponseStatus(code = OK)
//    public void delete(@PathVariable String identifier) {
//        return "Hello world from users service!";
//    }
//


}
