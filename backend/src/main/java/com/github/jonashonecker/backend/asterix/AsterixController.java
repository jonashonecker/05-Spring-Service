package com.github.jonashonecker.backend.asterix;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/characters")
public class AsterixController {

    private final AsterixRepository asterixRepository;

    public AsterixController(AsterixRepository asterixRepository) {
        this.asterixRepository = asterixRepository;
    }

    @GetMapping
    public List<Character> getAllCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String profession
    ) {
        List<Character> characters = asterixRepository.findAll();
        return characters.stream()
                .filter(c -> name == null || c.name().equals(name))
                .filter(c -> age == null || c.age() == age)
                .filter(c -> profession == null || c.profession().equals(profession))
                .collect(Collectors.toList());
    }

    @GetMapping("/statistics")
    public BigDecimal calcMeanAgeByProfession (@RequestParam String profession) {
        List<Character> characters = asterixRepository.findByProfessionAndReturnAgeOnly(profession);
        int sum = characters.stream().mapToInt(Character::age).sum();
        return new BigDecimal(sum).divide(BigDecimal.valueOf(characters.size()), 2, RoundingMode.HALF_UP);
    }

    @PostMapping
    public Character insertCharacter(@RequestBody Character newCharacter) {
        return asterixRepository.insert(newCharacter);
    }

    @PutMapping
    public Character updateCharacter(@RequestBody Character characterToUpdate) {
        return asterixRepository.save(characterToUpdate);
    }

    @DeleteMapping("{id}")
    public void deleteCharacter(@PathVariable String id) {
        asterixRepository.deleteById(id);
    }
}
