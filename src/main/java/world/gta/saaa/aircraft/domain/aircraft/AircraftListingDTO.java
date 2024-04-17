package world.gta.saaa.aircraft.domain.aircraft;

public record AircraftListingDTO(String brand, String model, String type, String tailNumber, String owner, boolean validTailNumber) {

    public AircraftListingDTO(Aircraft aircraft) {
        this(aircraft.getBrand(), aircraft.getModel(), aircraft.getType().toString(), aircraft.getTailNumber(), aircraft.getOwner().getName(), aircraft.validTailNumber());
    }
    
}