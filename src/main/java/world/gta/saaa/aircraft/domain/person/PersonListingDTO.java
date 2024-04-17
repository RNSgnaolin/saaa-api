package world.gta.saaa.aircraft.domain.person;

public record PersonListingDTO(
    String name,
    String representative,
    Long phone
) {

    public PersonListingDTO(Person person) {
        this(person.getName(), person.getRepresentative(), person.getPhone());
    }
    
}
