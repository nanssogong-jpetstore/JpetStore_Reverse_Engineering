package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.AnimalMating;

import java.util.List;

public interface AnimalMapper {
    void insertAnimal(AnimalMating animalMating);
    List<AnimalMating> getAnimalMatingList();
    AnimalMating getAnimalMattingDetail(int id);

    void plusViewCount(int id);

    List<AnimalMating> searchAnimalMatingList(String keywords);
}
