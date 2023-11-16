package mapstruct;

import autum.com.users.business.user.dto.CreateUserDto;
import autum.com.users.infrastructure.mapstruct.Mapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapperTest {

    @Test
    public void null_result() {
        var mapper = new Mapper(null);
        var result = mapper.map(null, CreateUserDto.class);
        assertNull(result);
    }
}