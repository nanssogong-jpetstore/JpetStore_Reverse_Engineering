package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.BoardLike;

import java.util.HashMap;
import java.util.List;

public interface LikeMapper {
    int checkLike(BoardLike boardLike);
    void Like(BoardLike boardLike);
    void unLike(BoardLike boardLike);
    void plusPreferCount(HashMap<String,Object> map);
    List<String> getAnimalCha(int id);
    void minusPreferCount(HashMap<String, Object> map);
}
