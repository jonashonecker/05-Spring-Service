package com.github.jonashonecker.backend.asterix;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsterixService {
    private final AsterixRepository asterixRepository;

    public AsterixService(AsterixRepository asterixRepository) {
        this.asterixRepository = asterixRepository;
    }

    public List<Character> getAllCharacters() {
        return asterixRepository.findAll();
    }

    public List<Character> getCharacterByProfession(String profession) {
        return asterixRepository.findByProfessionAndReturnAgeOnly(profession);
    }

    public Character postNewCharacter(Character newCharacter) {
        return asterixRepository.insert(newCharacter);
    }

    public Character putCharacter(Character characterToUpdate) {
        return asterixRepository.save(characterToUpdate);
    }

    public void deleteCharacterById(String id) {
        asterixRepository.deleteById(id);
    }
}
