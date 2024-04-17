package world.gta.saaa.aircraft.domain.aircraft;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

@Query(value = "SELECT aircrafts.* FROM aircrafts " +
"INNER JOIN persons ON aircrafts.person_id=persons.id " +
"WHERE brand LIKE %?1% OR model LIKE %?1% OR tail_number LIKE %?1% OR name LIKE %?1%", nativeQuery = true)
public List<Aircraft> findByQuery(String searchPattern);

@Query(value = "SELECT * FROM aircrafts " +
"WHERE NOT REGEXP_LIKE(tail_number, '^N[1-9]((\\\\d{0,4})|(\\\\d{0,3}[A-HJ-NP-Z])|(\\\\d{0,2}[A-HJ-NP-Z]{2}))$')", nativeQuery = true)
public List<Aircraft> findAllIrregularTailNumbers();

}