package world.gta.saaa.aircraft.domain.aircraft;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

@Query(value = "SELECT * FROM aircrafts WHERE active=1", nativeQuery = true)
public Page<Aircraft> findByActive(Pageable pageable);

@Query(value = "SELECT * FROM aircrafts WHERE active=1", nativeQuery = true)
public List<Aircraft> findByActive();

@Query(value = "SELECT aircrafts.* FROM aircrafts " +
"INNER JOIN persons ON aircrafts.person_id=persons.id " +
"WHERE (brand LIKE %?1% OR model LIKE %?1% OR tail_number LIKE %?1% OR name LIKE %?1%) " +
"AND active=1", nativeQuery = true)
public Page<Aircraft> findByQuery(String searchPattern, Pageable pageable);

}