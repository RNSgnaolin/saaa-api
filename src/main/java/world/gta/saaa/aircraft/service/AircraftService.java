package world.gta.saaa.aircraft.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.NonNull;
import world.gta.saaa.aircraft.domain.aircraft.Aircraft;
import world.gta.saaa.aircraft.domain.aircraft.AircraftDTO;
import world.gta.saaa.aircraft.domain.aircraft.AircraftListingDTO;
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

        Person person = personRepository.findById(data.owner()).orElseThrow(EntityNotFoundException::new);

        return new Aircraft(
            null,
            data.brand(),
            data.model(),
            data.tailNumber().toUpperCase(),
            data.callsign().toUpperCase(),
            true,
            false,
            data.type(),
            person
        );

    }

    public void update(@NonNull Aircraft aircraft, AircraftUpdateDTO data) {

        data.brand().ifPresent(aircraft::setBrand);
        data.model().ifPresent(aircraft::setModel);
        data.tailNumber().ifPresent(aircraft::setTailNumber);
        data.callsign().ifPresent(aircraft::setCallsign);
        data.type().ifPresent(aircraft::setType);

        data.personId().ifPresent(person -> {
            Person newPerson = personRepository.findById(data.personId().get()).orElseThrow(EntityNotFoundException::new);
            aircraft.setOwner(newPerson);
        });

    }

    public List<Aircraft> findIllegalAircrafts() {

        Predicate<Aircraft> illegal = aircraft -> !aircraft.validTailNumber();
        return aircraftRepository.findByActive()
            .stream()
            .filter(illegal)
            .toList();
    }
    
}
