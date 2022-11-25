package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.AnimalMating;
import org.mybatis.jpetstore.mapper.AnimalMapper;
import org.mybatis.jpetstore.mapper.MatingListMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnimalService {

    private final AnimalMapper animalMapper;
    private final MatingListMapper matingListMapper;

    public AnimalService(AnimalMapper animalMapper, MatingListMapper matingListMapper) {
        this.animalMapper = animalMapper;
        this.matingListMapper = matingListMapper;
    }



    public void insertAnimal(AnimalMating animalMating) {

        System.out.println(animalMating.getImgUrl());
        animalMapper.insertAnimal(animalMating);
    }

    public List<AnimalMating> getAnimalMatingList() {
        return matingListMapper.getAnimalMatingList();
    }

}
