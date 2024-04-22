package world.gta.saaa.aircraft.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import world.gta.saaa.aircraft.domain.aircraft.Aircraft;
import world.gta.saaa.aircraft.domain.aircraft.AircraftDTO;
import world.gta.saaa.aircraft.domain.aircraft.AircraftListingDTO;
import world.gta.saaa.aircraft.domain.aircraft.AircraftRepository;
import world.gta.saaa.aircraft.domain.classification.Classification;
import world.gta.saaa.aircraft.domain.person.Person;
import world.gta.saaa.aircraft.service.AircraftService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class AircraftControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AircraftDTO> aircraftDataJson;

    @Autowired
    private JacksonTester<AircraftListingDTO> aircraftListingJson;

    @Autowired
    private JacksonTester<List<AircraftListingDTO>> listOfAircraftListingJson;

    @MockBean
    private AircraftRepository aircraftRepository;

    @MockBean
    private AircraftService aircraftService;

    @Test
    @DisplayName("Should return HTTP Status 400")
    void testRegisterAircraft() throws Exception {

        var response = mvc.perform(MockMvcRequestBuilders.post("/aircrafts/register"))
            .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

/*     @Test
    @DisplayName("Should return HTTP Status 201")
    @SuppressWarnings("null")
    void testRegisterAircraft2() throws Exception {

        var data = new AircraftDTO("Brand", "Model", "N156AC", "N156AC", Classification.GOVERNMENT, 1l);
        var personData = new Person(1l, "John Doe", "John Doe", 7518l);
        var aircraftData = new Aircraft(1l, "Brand", "Model", "N156AC", "N156AC", false, true, Classification.GOVERNMENT, personData);

        var expectedJson = aircraftListingJson.write(
            new AircraftListingDTO("Brand", "Model", "GOVERNMENT", "N156AC", "N156AC", "John Doe", true)
        ).getJson();

        when(aircraftService.dataToObject(data)).thenReturn(aircraftData);
        when(aircraftService.getAircraftRepository()).thenReturn(aircraftRepository);

        var response = mvc.perform(MockMvcRequestBuilders.post("/aircrafts/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(aircraftDataJson.write(data).getJson()))
            .andReturn().getResponse();


        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedJson);

            
    }

    @Test
    @DisplayName("Should return invalid tail numbers correctly")
    void testInvalidTailNumbers() throws Exception {

        Person person = new Person(1l, "Government Company", "John Doe", 123456l);

        List<Aircraft> aircrafts = Arrays.asList(
            new Aircraft(1l, "Brand1", "Model1", "AIR 1", "AIR 1", false, true, Classification.GOVERNMENT, person),
            new Aircraft(2l, "Brand2", "Model2", "N156AC", "N156AC", false, true, Classification.GOVERNMENT, person),
            new Aircraft(3l, "Brand3", "Model3", "N12345", "N12345", false, true, Classification.GOVERNMENT, person),
            new Aircraft(4l, "Brand4", "Model4", "N15ABC", "N15ABC", false, true, Classification.GOVERNMENT, person),
            new Aircraft(5l, "Brand5", "Model5", "N156IB", "N156IB", false, true, Classification.GOVERNMENT, person),
            new Aircraft(6l, "Brand6", "Model6", "N156OC", "156OC", false, true, Classification.GOVERNMENT, person)
        );

        List<Aircraft> invalidAircrafts = Arrays.asList(
            new Aircraft(1l, "Brand1", "Model1", "AIR 1", "AIR 1", false, true, Classification.GOVERNMENT, person),
            new Aircraft(4l, "Brand4", "Model4", "N15ABC", "N15ABC", false, true, Classification.GOVERNMENT, person),
            new Aircraft(5l, "Brand5", "Model5", "N156IB", "N156IB", false, true, Classification.GOVERNMENT, person),
            new Aircraft(6l, "Brand6", "Model6", "N156OC", "N156OC", false, true, Classification.GOVERNMENT, person)
        );

        List<AircraftListingDTO> dataOfInvalid = invalidAircrafts.stream().map(AircraftListingDTO::new).collect(Collectors.toList());

        when(aircraftService.getAircraftRepository()).thenReturn(aircraftRepository);
        when(aircraftRepository.findAllIrregularTailNumbers()).thenReturn(regexForInvalidTailNumbers(aircrafts));

        var expectedJson = listOfAircraftListingJson.write(dataOfInvalid).getJson();
        var response = mvc.perform(MockMvcRequestBuilders.get("/aircrafts/invalid"))
            .andReturn()
            .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).contains(expectedJson);

    }

    List<Aircraft> regexForInvalidTailNumbers(List<Aircraft> aircrafts) {

        List<Aircraft> results = new ArrayList<>();

        for (Aircraft a : aircrafts) {
            if (!a.validTailNumber()) {
                results.add(a);
            }
        }

        return results;
    } */

}
