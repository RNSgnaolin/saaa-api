package world.gta.saaa.aircraft.domain.person;

/**
    Data Transfer Object (DTO) for listing Person information.
    This DTO is used to expose a summary of person details, such as in listings or search results.
    It is designed to be used in the PersonController for displaying person information without sensitive data.
 */

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
