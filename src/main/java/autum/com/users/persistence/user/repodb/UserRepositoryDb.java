package autum.com.users.persistence.user.repodb;

import autum.com.users.persistence.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepositoryDb extends CrudRepository<User, Long> {

    @Query(value = "SELECT u.*, " +
            "(SELECT COUNT(*) AS subscribers FROM subscriptions subs WHERE u.id = subs.to_user_id), " +
            "(SELECT COUNT(*) AS subscriptions FROM subscriptions subs WHERE u.id = subs.user_id) " +
            "FROM users u " +
            "WHERE u.identifier = :identifier AND u.status <> 4", nativeQuery = true)
    User findByIdentifierWithSubscrCount(String identifier);

    @Query(value = "SELECT * FROM users WHERE " +
            "to_tsvector('simple', first_name) || to_tsvector('simple', last_name) @@ to_tsquery('simple', :name)",
            nativeQuery = true)
    Page<User> findByName(String name, Pageable pageable);

    //TODO email может повторяться в статусе DELETED!!!
    boolean existsByEmail(String email);

    User findByIdentifier(String identifier);

    List<User> findAllByIdentifierIn(Collection<String> identifiers);
}