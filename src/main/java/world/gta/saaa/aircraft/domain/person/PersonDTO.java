package world.gta.saaa.aircraft.domain.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonDTO(
    @NotBlank
    String name,
    String representative,
    @NotNull
    Long phone
) {

}
