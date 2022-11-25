package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.AnimalMating;
import org.mybatis.jpetstore.mapper.AnimalMapper;
import org.mybatis.jpetstore.mapper.MatingListMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnimalService {

    private final AnimalMapper animalMapper;

    public AnimalService(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }

    public void insertAnimal(AnimalMating animalMating) {

        System.out.println(animalMating.getImgUrl());
        animalMapper.insertAnimal(animalMating);
    }

    public List<AnimalMating> getAnimalMatingList() { return animalMapper.getAnimalMatingList(); }

}
