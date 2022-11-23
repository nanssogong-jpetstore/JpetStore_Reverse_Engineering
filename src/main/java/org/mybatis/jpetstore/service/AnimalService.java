package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.AnimalMating;
import org.mybatis.jpetstore.mapper.AnimalMapper;
import org.springframework.stereotype.Service;



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
}
