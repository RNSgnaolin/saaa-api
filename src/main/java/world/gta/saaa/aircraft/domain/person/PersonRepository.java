package world.gta.saaa.aircraft.domain.person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM persons " +
    "WHERE name LIKE %?1% OR representative LIKE %?1%", nativeQuery = true)
    public List<Person> findByQuery(String searchPattern);

    @Query(value = "SELECT * FROM persons", nativeQuery = true)
    public List<Person> findPersonOptions();
    
}
