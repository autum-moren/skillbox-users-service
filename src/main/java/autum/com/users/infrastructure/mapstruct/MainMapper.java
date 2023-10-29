package autum.com.users.infrastructure.mapstruct;

public interface MainMapper<T, E> {
    E map(T t);
}