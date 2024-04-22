package world.gta.saaa.aircraft.domain.person;

public record PersonListingDTO(
    Long id,
    String name,
    String representative,
    Long phone
) {

    public PersonListingDTO(Person person) {
        this(person.getId(), person.getName(), person.getRepresentative(), person.getPhone());
    }
    
}
