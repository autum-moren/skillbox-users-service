package autum.com.users.api;

import autum.com.users.api.request.CreateUserRequest;
import autum.com.users.api.request.UpdateUserRequest;
import autum.com.users.api.response.UserListResponse;
import autum.com.users.api.response.UserResponse;
import autum.com.users.business.user.UserService;
import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.business.user.dto.UpdateUserDto;
import autum.com.users.infrastructure.mapstruct.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;


    @PostMapping("/create")
    @ResponseStatus(code = OK)
    public void create(@RequestBody CreateUserRequest request) {
        var dto = mapper.map(request, CreateUserDto.class);
        userService.createUser(dto);
    }

    //TODO личная информация, должен быть вариант общего назначения
    @GetMapping(value = "/{identifier}")
    public UserResponse get(@PathVariable String identifier) {
        var dto = userService.getUser(identifier);
        return mapper.map(dto, UserResponse.class);
    }

    //TODO getList, setEmail, addSkill, deleteSkill
    //TODO так же необходимо восстановление аккаунта после деактивации

    @PostMapping("/{identifier}/update")
    @ResponseStatus(code = OK)
    public void update(@RequestBody UpdateUserRequest request, @PathVariable String identifier) {
        var dto = mapper.map(request, UpdateUserDto.class);
        userService.updateUser(dto, identifier);
    }

    @DeleteMapping("/{identifier}")
    @ResponseStatus(code = OK)
    public void delete(@PathVariable String identifier) {
        userService.deactivateUser(identifier);
    }

    //TODO запрос на откат временного удаления

    @GetMapping(value = "/list")
    public UserListResponse getUserList(String name, Pageable pageable) {
        var users = userService.getUserListByName(name, pageable);
        return mapper.map(users, UserListResponse.class);
    }
}