package world.gta.saaa.aircraft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import world.gta.saaa.aircraft.domain.user.User;
import world.gta.saaa.aircraft.domain.user.UserDTO;
import world.gta.saaa.aircraft.domain.user.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    private UserRepository repository;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO data) {
        User user = new User(data);
        repository.save(user);
        return ResponseEntity.ok(data);
    }
    
}
