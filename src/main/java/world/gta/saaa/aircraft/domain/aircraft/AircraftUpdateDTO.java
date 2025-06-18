package world.gta.saaa.aircraft.domain.aircraft;

import java.util.Optional;

import world.gta.saaa.aircraft.domain.classification.Classification;

/**
    Data Transfer Object (DTO) for updating Aircraft.
    This DTO is used for updating existing aircraft information.
    It allows optional fields to be updated, making it flexible for partial updates.
    It is designed to be used in the AircraftController for updating aircraft details.
 */

public record AircraftUpdateDTO(Optional<String> brand, Optional<String> model, 
Optional<String> tailNumber, Optional<String> callsign, Optional<Boolean> pending, 
Optional<Boolean> active, Optional<Classification> type, Optional<Long> personId) {
    
}
