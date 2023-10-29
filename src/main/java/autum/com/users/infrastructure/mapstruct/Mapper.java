package autum.com.users.infrastructure.mapstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ApplicationContext applicationContext;

    public <T, R> R map(T from, Class<R> to) {
        if (from == null) {
            return null;
        }
        var type = ResolvableType.forClassWithGenerics(MainMapper.class, from.getClass(), to);
        try {
            MainMapper<T, R> mapper = (MainMapper<T, R>) applicationContext.getBeanProvider(type).getObject();
            return mapper.map(from);
        } catch (NoSuchBeanDefinitionException e) {
            throw new IllegalArgumentException("You need to add the mapstruct interface:\n" + type);
        }
    }
}