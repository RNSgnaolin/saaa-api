package world.gta.saaa.aircraft.domain.aircraft;

import java.util.Optional;

import world.gta.saaa.aircraft.domain.classification.Classification;

public record AircraftUpdateDTO(Optional<String> brand, Optional<String> model, 
Optional<String> tailNumber, Optional<String> callsign, Optional<Classification> type, Optional<Long> personId) {
    
}
