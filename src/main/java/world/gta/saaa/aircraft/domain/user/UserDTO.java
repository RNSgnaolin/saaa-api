package world.gta.saaa.aircraft.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    @NotBlank
    String login,
    @NotBlank 
    String password) {
    
}
