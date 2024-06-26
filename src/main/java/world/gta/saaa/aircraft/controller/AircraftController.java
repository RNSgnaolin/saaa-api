package world.gta.saaa.aircraft.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NonNull;
import world.gta.saaa.aircraft.domain.aircraft.Aircraft;
import world.gta.saaa.aircraft.domain.aircraft.AircraftDTO;
import world.gta.saaa.aircraft.domain.aircraft.AircraftListingDTO;
import world.gta.saaa.aircraft.domain.aircraft.AircraftUpdateDTO;
import world.gta.saaa.aircraft.service.AircraftService;
import world.gta.saaa.aircraft.service.PageListService;

@RestController
@RequestMapping("/aircrafts")
public class AircraftController {

    AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    private AircraftService aircraftService;
    private PageListService pageListService = new PageListService();

    @GetMapping
    public ResponseEntity<Page<AircraftListingDTO>> listAircrafts(Pageable pageable, @RequestParam(value = "query") Optional<String> searchPattern) {
        
        if (!searchPattern.isPresent()) {

            return ResponseEntity.ok(
                pageListService.listToPage(aircraftService.getAircraftRepository().findAll(), pageable)
                .map(AircraftListingDTO::new)
            );
                
        }

        return ResponseEntity.ok(
            pageListService.listToPage(aircraftService.getAircraftRepository().findByQuery(searchPattern.get()), pageable)
            .map(AircraftListingDTO::new)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftListingDTO> listAircraftById(@NonNull @PathVariable Long id) {
        return ResponseEntity.ok(
            new AircraftListingDTO(
                aircraftService.getAircraftRepository()
                .findById(id)
                .orElseThrow(EntityNotFoundException::new)
            ));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AircraftListingDTO> updateAircraft (@RequestBody AircraftUpdateDTO data, @PathVariable @NonNull Long id) {

        Aircraft aircraft = aircraftService.getAircraftRepository().findById(id).orElseThrow(EntityNotFoundException::new);
        aircraftService.update(aircraft, data);
        return ResponseEntity.ok(new AircraftListingDTO(aircraft));

    }

 
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<AircraftListingDTO> registerAircraft(@RequestBody @Valid AircraftDTO data, UriComponentsBuilder uriBuilder) {

        Aircraft aircraft = aircraftService.dataToObject(data);
        aircraftService.getAircraftRepository().save(aircraft);

        var uri = uriBuilder.path("/aircrafts/register/{id}").buildAndExpand(aircraft.getId()).toUri();
        return ResponseEntity.created(uri).body(new AircraftListingDTO(aircraft));
    }
    
}
