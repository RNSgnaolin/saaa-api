package world.gta.saaa.aircraft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NonNull;
import world.gta.saaa.aircraft.domain.person.Person;
import world.gta.saaa.aircraft.domain.person.PersonDTO;
import world.gta.saaa.aircraft.domain.person.PersonListingDTO;
import world.gta.saaa.aircraft.domain.person.PersonOptionsDTO;
import world.gta.saaa.aircraft.domain.person.PersonUpdateDTO;
import world.gta.saaa.aircraft.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    PersonController(PersonService personService) {
        this.personService = personService;
    }

    private PersonService personService;

    @GetMapping
    public ResponseEntity<Page<PersonListingDTO>> findPersonByQuery(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "query") Optional<String> searchPattern) {

        if (!searchPattern.isPresent()) {
            return ResponseEntity.ok(
                personService.getRepository()
                    .findAll(pageable)
                    .map(p -> new PersonListingDTO(p)));
        }

        return ResponseEntity.ok(
            personService.getRepository()
                .findByQuery(searchPattern.get(), pageable)
                .map(p -> new PersonListingDTO(p)));
    }

    @GetMapping("/options") 
    public ResponseEntity<List<PersonOptionsDTO>> findPersonOptions(@PageableDefault(size = 10) Pageable pageable) {

        return ResponseEntity.ok(
            personService.getRepository()
                .findPersonOptions(pageable)
                .stream()
                .map(PersonOptionsDTO::new)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonListingDTO> findPersonById(@NonNull @PathVariable Long id) {

        return ResponseEntity.ok(
            new PersonListingDTO(
                personService.getRepository()
                .findById(id)
                .orElseThrow(EntityNotFoundException::new)));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<PersonListingDTO> registerPerson(@RequestBody @Valid PersonDTO data, UriComponentsBuilder uriBuilder) {
        
        Person person = new Person(data);
        personService.getRepository().save(person);

        var uri = uriBuilder.path("/register/{id}")
                    .buildAndExpand(person.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new PersonListingDTO(person));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PersonListingDTO> updatePerson(@RequestBody @Valid PersonUpdateDTO data, @PathVariable @NonNull Long id) {

        Person person = personService.getRepository().findById(id).orElseThrow(EntityNotFoundException::new);
        personService.update(person, data);
        return ResponseEntity.ok(new PersonListingDTO(person));
        
    }


}
