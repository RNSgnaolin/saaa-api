package world.gta.saaa.aircraft.domain.user;

import jakarta.validation.constraints.NotBlank;

/**
    Data Transfer Object (DTO) for User.
    This DTO is used for user registration and contains the necessary fields.
    It is designed to be used in the UserController for creating new users without allowing params such as admin.
 */

public record UserDTO(
    @NotBlank
    String login,
    @NotBlank 
    String password) {
    
}
