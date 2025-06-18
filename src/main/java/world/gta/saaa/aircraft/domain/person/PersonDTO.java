package world.gta.saaa.aircraft.domain.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
    Data Transfer Object (DTO) for Person.
    This DTO is used for person registration and contains the necessary fields.
    It is designed to be used in the PersonController for creating new persons without allowing sensitive params that may potentially occur in the future.
 */

public record PersonDTO(
    @NotBlank
    String name,
    String representative,
    @NotNull
    Long phone
) {

}
