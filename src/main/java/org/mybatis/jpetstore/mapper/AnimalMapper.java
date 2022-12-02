package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.AnimalMating;

import java.util.List;
import java.util.Map;

public interface AnimalMapper {
    int insertAnimal(AnimalMating animalMating);
    void editAnimal(AnimalMating animalMating);
    List<AnimalMating> getAnimalMatingList(Map<String, Object> map);
    AnimalMating getAnimalMattingDetail(int id);
    Integer getAnimalMatingCount();
    void plusViewCount(int id);
    List<AnimalMating> searchAnimalMatingTitle(Map<String, Object> map);
    List<AnimalMating> searchAnimalMatingContents(Map<String, Object> map);
    List<AnimalMating> searchAnimalMatingUser(Map<String, Object> map);
    Integer searchAnimalMatingTitleCount(Map<String, Object> map);
    Integer searchAnimalMatingContentsCount(Map<String, Object> map);
    Integer searchAnimalMatingUserCount(Map<String, Object> map);

    List<AnimalMating> getRecommendAnimalList(Map<String, Object> map);
    Integer getRecommendMatingCount(String id);
    List<AnimalMating> searchRecommendAnimalTitle(Map<String, Object> map);
    List<AnimalMating> searchRecommendAnimalContents(Map<String, Object> map);
    List<AnimalMating> searchRecommendAnimalUser(Map<String, Object> map);

    Integer searchRecommendAnimalTitleCount(Map<String, Object> map);
    Integer searchRecommendAnimalContentsCount(Map<String, Object> map);
    Integer searchRecommendAnimalUserCount(Map<String, Object> map);

    void addCharacter(Map<String, Object> animalCharacter);

}
