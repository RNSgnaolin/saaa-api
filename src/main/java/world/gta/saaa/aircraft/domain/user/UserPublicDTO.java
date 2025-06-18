package world.gta.saaa.aircraft.domain.user;

import jakarta.validation.constraints.NotBlank;

/** 
    Add more information if needed, such as email, admin access, etc.
    This DTO is used to expose public user information without sensitive data like password.
    It is designed to be used in the UserController for displaying user information in public contexts.
    Might be further extended in the future to include more user details.
*/

public record UserPublicDTO(
    @NotBlank
    String login
) {

    public UserPublicDTO(User user) {
        this(user.getLogin());
    }

}
