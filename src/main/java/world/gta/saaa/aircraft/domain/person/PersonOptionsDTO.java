package world.gta.saaa.aircraft.domain.person;

public record PersonOptionsDTO(Long id, String name) {

    public PersonOptionsDTO(Person person) {
        this(person.getId(), person.getName());
    }
    
}
