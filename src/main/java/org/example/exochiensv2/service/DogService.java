package org.example.exochiensv2.service;

import org.example.exochiensv2.model.Dog;
import org.example.exochiensv2.repository.DogRepository;

import java.time.LocalDate;
import java.util.List;

public class DogService {
    private DogRepository dogRepository;

    public DogService() {
        this.dogRepository = new DogRepository();
    }

    public Dog create(String name, String breed, LocalDate dateOfBirth) {
        Dog dog = Dog.builder().name(name).breed(breed).dateOfBirth(dateOfBirth).build();
        return dogRepository.create(dog);
    }

    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    public Dog findById(int id) {
        return dogRepository.findById(id);
    }
}
