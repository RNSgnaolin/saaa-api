package world.gta.saaa.aircraft.domain.aircraft;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import world.gta.saaa.aircraft.domain.classification.Classification;

/**
    Data Transfer Object (DTO) for Aircraft.
    This DTO is used for aircraft registration and contains the necessary fields.
    It is designed to be used in the AircraftController for creating new aircraft without allowing params such as pending and active.
 */

public record AircraftDTO(
    @NotBlank
    String brand,
    @NotBlank
    String model,
    @NotBlank
    String tailNumber,
    @NotBlank
    String callsign,
    @NotNull
    Classification type,
    @NotNull
    Long owner
    ) {
    
}
