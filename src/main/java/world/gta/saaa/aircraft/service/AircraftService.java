package world.gta.saaa.aircraft.service;

import java.util.List;
import java.util.function.Predicate;

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

    /**
     * Converts the DTO into an Aircraft object.
     * This method is used when registering a new Aircraft.
     * It retrieves the owner from the repository using the Person ID provided in the DTO.
     */
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

    /**
     * Updates the given Aircraft object with the provided data.
     * DTO uses Optional fields to allow partial updates.
     * If a field is not present in the DTO, it will not be updated.
     */
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

    /**
     * Returns all active aircrafts that have an invalid tail number.
     * Although it's only used in GET listing at the moment, will return them as an Aircraft object for scalability.
     */
    public List<Aircraft> findIllegalAircrafts() {

        Predicate<Aircraft> illegal = aircraft -> !aircraft.validTailNumber();
        return aircraftRepository.findByActive()
            .stream()
            .filter(illegal)
            .toList();
    }
    
}
