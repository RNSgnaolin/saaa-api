package world.gta.saaa.aircraft.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NonNull;
import world.gta.saaa.aircraft.domain.user.User;
import world.gta.saaa.aircraft.domain.user.UserDTO;
import world.gta.saaa.aircraft.domain.user.UserPublicDTO;
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

    @GetMapping
    public ResponseEntity<Page<UserPublicDTO>> listUsers(@PageableDefault(size = 10) Pageable pageable) {

        return ResponseEntity.ok(
            repository.findAll(pageable)
                .map(UserPublicDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPublicDTO> getUserById(@NonNull @PathVariable Long id) {

        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new UserPublicDTO(user));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDTO> updateUser(@NonNull @PathVariable Long id, @RequestBody @Valid UserDTO data) {
        // Must ensure the user is logged in before updating
        // Add admin authentication check to change user admin data? Still not sure about public access for API, might block all endpoints

        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setPassword(data.password());
        repository.save(user);
        return ResponseEntity.ok(data);
    }

}
