package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.AnimalMating;

import java.util.List;

public interface AnimalMapper {
    void insertAnimal(AnimalMating animalMating);
    List<AnimalMating> getAnimalMatingList();
}
