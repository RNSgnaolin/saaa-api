package world.gta.saaa.aircraft.service;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NonNull;
import world.gta.saaa.aircraft.domain.person.Person;
import world.gta.saaa.aircraft.domain.person.PersonRepository;
import world.gta.saaa.aircraft.domain.person.PersonUpdateDTO;

@Service
@Getter
public class PersonService {

    PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    private PersonRepository repository;

    public void update(@NonNull Person person, PersonUpdateDTO data) {

        if (data.name() != null)
            person.setName(data.name());
        if (data.representative() != null)
            person.setRepresentative(data.representative());
        if (data.phone() != null)
            person.setPhone(data.phone());

        repository.save(person);

    }
    
}
