package world.gta.saaa.aircraft.domain.aircraft;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import world.gta.saaa.aircraft.domain.classification.Classification;

public record AircraftDTO(
    @NotBlank
    String brand,
    @NotBlank
    String model,
    @NotBlank
    String tailNumber,
    @NotNull
    Classification type,
    @NotNull
    Long owner
    ) {
    
}
