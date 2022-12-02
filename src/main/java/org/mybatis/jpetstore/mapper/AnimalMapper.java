package org.mybatis.jpetstore.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.jpetstore.domain.AnimalMating;

import java.util.List;
import java.util.Map;

public interface AnimalMapper {
    int insertAnimal(AnimalMating animalMating);
    void editAnimal(AnimalMating animalMating);
    AnimalMating getAnimalMattingDetail(@Param("userId")String userId, @Param("id") int id);
    void plusViewCount(int id);
    void userAnimalDelete(int id);
    List<String> listDelCharacter(int id);
    List<String> getCharacterList(int id);
    void deleteOldCharacter(Map<String, Object> map);
    Integer getRecommendMatingCount(String id);
    void addCharacter(Map<String, Object> animalCharacter);
    void updateStatus(Map<String, Object> map);
    String getStatus(Map<String, Object> map);
    String getUserIdByPostId(int postId);
    void editCharacter(Map<String, Object> animalCharacter);
    List<String> getAnimalMatingCha(int id);
    
    //페이징
    List<AnimalMating> getAnimalMating(Map<String, Object> map);
    Integer getAnimalMatingCount(Map<String, Object> map);
    List<AnimalMating> getRecommendAnimalMating(Map<String, Object> map);
    Integer getRecommendAnimalMatingCount(Map<String, Object> map);

}
