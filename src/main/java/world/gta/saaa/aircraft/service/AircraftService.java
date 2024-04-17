package world.gta.saaa.aircraft.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.NonNull;
import world.gta.saaa.aircraft.domain.aircraft.Aircraft;
import world.gta.saaa.aircraft.domain.aircraft.AircraftDTO;
import world.gta.saaa.aircraft.domain.aircraft.AircraftRepository;
import world.gta.saaa.aircraft.domain.aircraft.AircraftUpdateDTO;
import world.gta.saaa.aircraft.domain.person.Person;
import world.gta.saaa.aircraft.domain.person.PersonRepository;

@Service
@Getter
public class AircraftService {

    public AircraftService(AircraftRepository aircraftRepository, PersonRepository personRepository) {
        this.aircraftRepository = aircraftRepository;
        this.personRepository = personRepository;
    }

    private AircraftRepository aircraftRepository;
    private PersonRepository personRepository;

    public Aircraft dataToObject(@NonNull AircraftDTO data) {

        @SuppressWarnings("null")
        Person person = personRepository.findById(data.owner()).orElseThrow(EntityNotFoundException::new);

        return new Aircraft(
            null,
            data.brand(),
            data.model(),
            data.tailNumber().toUpperCase(),
            data.type(),
            person
        );

    }

    public void update(@NonNull Aircraft aircraft, AircraftUpdateDTO data) {

        if (data.brand() != null)
            aircraft.setBrand(data.brand());

        if (data.model() != null)
            aircraft.setModel(data.model());

        if (data.tailNumber() != null)
            aircraft.setTailNumber(data.tailNumber());

        if (data.type() != null)
            aircraft.setType(data.type());
            
        if (data.personId() != null) {
            Person person = personRepository.findById(data.personId()).orElseThrow(EntityNotFoundException::new);
            aircraft.setOwner(person);
        }
        
        aircraftRepository.save(aircraft);

    }
    
}
