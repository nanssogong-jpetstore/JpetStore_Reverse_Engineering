package org.mybatis.jpetstore.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.jpetstore.domain.AnimalMating;

import java.util.List;
import java.util.Map;

public interface AnimalMapper {
    int insertAnimal(AnimalMating animalMating);
    void editAnimal(AnimalMating animalMating);
    List<AnimalMating> getAnimalMatingList(Map<String, Object> map);
    AnimalMating getAnimalMattingDetail(@Param("userId")String userId, @Param("id") int id);
    Integer getAnimalMatingCount();
    void plusViewCount(int id);
    List<AnimalMating> searchAnimalMatingTitle(Map<String, Object> map);
    List<AnimalMating> searchAnimalMatingContents(Map<String, Object> map);
    List<AnimalMating> searchAnimalMatingUser(Map<String, Object> map);
    Integer searchAnimalMatingTitleCount(Map<String, Object> map);
    Integer searchAnimalMatingContentsCount(Map<String, Object> map);
    Integer searchAnimalMatingUserCount(Map<String, Object> map);
    void userAnimalDelete(int id);
    List<String> listDelCharacter(int id);
    List<String> getCharacterList(int id);
    void deleteOldCharacter(Map<String, Object> map);

    List<AnimalMating> getRecommendAnimalList(Map<String, Object> map);
    Integer getRecommendMatingCount(String id);
    List<AnimalMating> searchRecommendAnimalTitle(Map<String, Object> map);
    List<AnimalMating> searchRecommendAnimalContents(Map<String, Object> map);
    List<AnimalMating> searchRecommendAnimalUser(Map<String, Object> map);

    Integer searchRecommendAnimalTitleCount(Map<String, Object> map);
    Integer searchRecommendAnimalContentsCount(Map<String, Object> map);
    Integer searchRecommendAnimalUserCount(Map<String, Object> map);

    void addCharacter(Map<String, Object> animalCharacter);
    void updateStatus(Map<String, Object> map);
    String getStatus(Map<String, Object> map);
    String getUserIdByPostId(int postId);

    void editCharacter(Map<String, Object> animalCharacter);

    List<String> getAnimalMatingCha(int id);

}
