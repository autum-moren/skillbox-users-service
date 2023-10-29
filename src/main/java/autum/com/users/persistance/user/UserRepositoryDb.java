package autum.com.users.persistance.user;

import autum.com.users.persistance.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryDb extends CrudRepository<User, Long> {

    @Query(value = "SELECT u.*, " +
            "(SELECT COUNT(*) AS subscribers FROM users_scheme.subscriptions subs WHERE u.id = subs.to_user_id), " +
            "(SELECT COUNT(*) AS subscriptions FROM users_scheme.subscriptions subs WHERE u.id = subs.user_id) " +
            "FROM users_scheme.users u " +
            "WHERE u.identifier = 'NMNMNNM4123423GFFFG' AND u.status = 1;", nativeQuery = true)
    User findByIdentifierWithSubscrCount(String identifier);

    User findByIdentifier(String identifier);
}