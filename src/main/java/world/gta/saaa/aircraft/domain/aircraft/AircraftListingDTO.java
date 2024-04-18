package world.gta.saaa.aircraft.domain.aircraft;

public record AircraftListingDTO(String brand, String model, String type, String tailNumber, String callsign, String owner, boolean validTailNumber) {

    public AircraftListingDTO(Aircraft aircraft) {
        this(aircraft.getBrand(), aircraft.getModel(), aircraft.getType().toString(), aircraft.getTailNumber(), aircraft.getCallsign(), aircraft.getOwner().getName(), aircraft.validTailNumber());
    }
    
}