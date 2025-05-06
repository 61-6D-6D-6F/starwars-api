package com.example.starwarsapi;

import com.example.starwarsapi.controller.CharacterController;
import com.example.starwarsapi.dto.CharacterRequest;
import com.example.starwarsapi.persistence.entity.Character;
import com.example.starwarsapi.persistence.entity.Planet;
import com.example.starwarsapi.persistence.entity.Specie;
import com.example.starwarsapi.persistence.repository.CharacterRepository;
import com.example.starwarsapi.persistence.repository.CharacterRepositoryImpl;
import com.example.starwarsapi.service.CharacterServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StarWarsApiTest {

    private static CharacterRepository characterRepository;
    private static CharacterServiceImpl characterService;
    private static CharacterController characterController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp() {
        characterRepository = new CharacterRepositoryImpl();
        characterService = new CharacterServiceImpl(characterRepository);
        characterController = new CharacterController(characterService);
    }

    @DisplayName("Task 1. Implement the method isCharacterOldWookie()")
    @ParameterizedTest
    @CsvSource({
            "3, true",   // Old Wookie
            "12, true",  // Sixty-year-old Wookie
            "1, false",  // Human character
            "8, false"   // Droid character
    })
    @Order(1)
    void testIsCharacterOldWookiee(Integer characterId, boolean expectedResult) {
        boolean result = characterService.isCharacterOldWookiee(characterId);
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("Task 2. Implement the method isCharacterTallerThanAverageHeightOfSpecie()")
    @ParameterizedTest
    @CsvSource({
            "1, true",   // Tall Human
            "3, true",  // Tall Wookiee
            "2, false",  // Short Human
            "10, false"   // Short Zabrak
    })
    @Order(2)
    void testIsCharacterTallerThanAverageHeightOfSpecie(Integer characterId, boolean expectedResult) {
        boolean result = characterService.isCharacterTallerThanAverageHeightOfSpecie(characterId);
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("Task 3. Implement the method getAverageWeightOfAllCharacters()")
    @Test
    @Order(3)
    void testGetAverageWeightOfAllCharacters() {
        Double result = characterService.getAverageWeightOfAllCharacters();
        assertThat(result).isEqualTo(134.47619047619048);
    }

    @DisplayName("Task 4. Find and fix the bug in the method getHeaviestCharacterByEachSpecie()")
    @Test
    @Order(4)
    void testGetHeaviestCharacterByEachSpecie() {
        List<Character> result = characterService.getHeaviestCharacterByEachSpecie();
        List<Character> expectedResult = getHeaviestCharacters();
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("Task 5. Add age validation for createCharacter()")
    @ParameterizedTest
    @MethodSource("characterAndStatusProvider")
    @Order(5)
    void testCreateCharacter(CharacterRequest characterRequest, HttpStatus expectedStatus) {
        ResponseEntity<Character> response = characterController.createCharacter(characterRequest);
        assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @DisplayName("Task 6. Implement the method getHeaviestCharacterOnPlanet()")
    @ParameterizedTest
    @MethodSource("planetNameAndResponseProvider")
    @Order(6)
    void testGetHeaviestCharacterOnPlanet(String planetName, ResponseEntity<Character> expectedResponse) {
        ResponseEntity<Character> response = characterController.getHeaviestCharacterOnPlanet(planetName);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @DisplayName("Task 7. Add exception handling in the method createCharacter()")
    @ParameterizedTest
    @MethodSource("characterAndStatusProviderForExceptions")
    @Order(7)
    void testCreateCharacterWithExceptions(CharacterRequest characterRequest, HttpStatus expectedStatus) {
        ResponseEntity<Character> response = characterController.createCharacter(characterRequest);
        assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @DisplayName("Task 7. Add exception handling in the method deleteCharacter()")
    @ParameterizedTest
    @MethodSource("characterIdAndStatusProvider")
    @Order(8)
    void testDeleteCharacter(Integer characterId, HttpStatus expectedStatus) {
        ResponseEntity<Void> response = characterController.deleteCharacter(characterId);
        assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @DisplayName("Task 8. Add global exception handling")
    @Test
    @Order(9)
    void testGlobalExceptionHandling() throws Exception {
        mockMvc.perform(put("/starwars-api/v1/characters/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
    }

    private static List<Character> getHeaviestCharacters() {
        Character heaviestHuman = characterRepository.getCharacterById(7).get();
        Character heaviestKaminoan = characterRepository.getCharacterById(19).get();
        Character heaviestWookiee = characterRepository.getCharacterById(3).get();
        Character heaviestCalamari = characterRepository.getCharacterById(14).get();
        Character heaviestZabrak = characterRepository.getCharacterById(10).get();
        Character heaviestGungan = characterRepository.getCharacterById(11).get();
        Character heaviestHutt = characterRepository.getCharacterById(5).get();
        Character heaviestDroid = characterRepository.getCharacterById(8).get();
        Character heaviestEwok = characterRepository.getCharacterById(18).get();
        return Arrays.asList(heaviestHuman, heaviestKaminoan, heaviestWookiee, heaviestCalamari,
                heaviestZabrak, heaviestGungan, heaviestHutt, heaviestDroid, heaviestEwok);
    }

    private static Stream<Arguments> characterAndStatusProvider() {
        Planet tatooine = characterRepository.getCharacterById(1).get().getPlanet();
        Specie human = characterRepository.getCharacterById(1).get().getSpecie();
        CharacterRequest invalidCharacter1 = new CharacterRequest(172, 77, -5, tatooine, human, "Test Name 1");
        CharacterRequest invalidCharacter2 = new CharacterRequest(172, 77, 10005, tatooine, human, "Test Name 2");
        CharacterRequest validCharacter1 = new CharacterRequest(172, 77, 1000, tatooine, human, "Test Name 3");
        CharacterRequest validCharacter2 = new CharacterRequest(172, 77, 20, tatooine, human, "Test Name 4");
        return Stream.of(
                Arguments.of(invalidCharacter1, HttpStatus.BAD_REQUEST),
                Arguments.of(invalidCharacter2, HttpStatus.BAD_REQUEST),
                Arguments.of(validCharacter1, HttpStatus.OK),
                Arguments.of(validCharacter2, HttpStatus.OK)
        );
    }

    private static Stream<Arguments> planetNameAndResponseProvider() {
        Character heaviesOnTatooine = characterRepository.getCharacterById(7).get();
        Character heaviesOnNaboo = characterRepository.getCharacterById(11).get();
        Character heaviesOnCorellia = characterRepository.getCharacterById(4).get();

        return Stream.of(
                Arguments.of("Random_planet", ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)),
                Arguments.of("Tatooine", ResponseEntity.status(HttpStatus.OK).body(heaviesOnTatooine)),
                Arguments.of("Naboo", ResponseEntity.status(HttpStatus.OK).body(heaviesOnNaboo)),
                Arguments.of("Corellia", ResponseEntity.status(HttpStatus.OK).body(heaviesOnCorellia))
        );
    }

    private static Stream<Arguments> characterAndStatusProviderForExceptions() {
        Planet tatooine = characterRepository.getCharacterById(1).get().getPlanet();
        Specie human = characterRepository.getCharacterById(1).get().getSpecie();
        CharacterRequest existingCharacterByName = new CharacterRequest(180, 80, 35, tatooine, human, "Han Solo");
        CharacterRequest newCharacter = new CharacterRequest(172, 77, 20, tatooine, human, "New Test Name");
        return Stream.of(
            Arguments.of(existingCharacterByName, HttpStatus.BAD_REQUEST),
            Arguments.of(newCharacter, HttpStatus.OK)
        );
    }

    private static Stream<Arguments> characterIdAndStatusProvider() {
        Integer unexistingCharacterId = 9999;
        Integer existingCharacterId = 17;
        return Stream.of(
            Arguments.of(unexistingCharacterId, HttpStatus.NOT_FOUND),
            Arguments.of(existingCharacterId, HttpStatus.OK)
        );
    }
}
