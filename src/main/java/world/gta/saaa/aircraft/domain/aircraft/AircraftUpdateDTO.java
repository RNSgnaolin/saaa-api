package world.gta.saaa.aircraft.domain.aircraft;

import world.gta.saaa.aircraft.domain.classification.Classification;

public record AircraftUpdateDTO(String brand, String model, String tailNumber, Classification type, Long personId) {
    
}
