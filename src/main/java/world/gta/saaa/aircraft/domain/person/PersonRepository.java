package world.gta.saaa.aircraft.domain.person;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM persons " +
    "WHERE name LIKE %?1% OR representative LIKE %?1%", nativeQuery = true)
    public Page<Person> findByQuery(String searchPattern, Pageable pageable);

    @Query(value = "SELECT * FROM persons", nativeQuery = true)
    public Page<Person> findPersonOptions(Pageable pageable);
    
}
