package autum.com.users.persistence.city.repodb;

import autum.com.users.persistence.city.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepositoryDb extends PagingAndSortingRepository<City, Long> {

    @Query(value = "SELECT * FROM cities WHERE name ILIKE CONCAT('%',:name,'%')", nativeQuery = true)
    Page<City> findAllByNameLike(String name, Pageable pageable);

    City findByIdentifier(String identifier);

}