package org.mybatis.jpetstore.Like;

import org.mybatis.jpetstore.domain.BoardLike;
import org.mybatis.jpetstore.mapper.ChatMapper;
import org.mybatis.jpetstore.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LikeService {
    private final LikeMapper likeMapper;

    @Autowired
    public LikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    public int checkLike(BoardLike boardLike) {
        return likeMapper.checkLike(boardLike);
    }

    public void unLike(BoardLike boardLike) {
        likeMapper.unLike(boardLike);
    }

    public void Like(BoardLike boardLike) {
        likeMapper.Like(boardLike);
    }

    public List<String> getAnimalCha(int id) {
        return likeMapper.getAnimalCha(id);
    }
    public void plusPrefer(String id, String character) {
        HashMap<String, Object> map = new HashMap();
        map.put("userId", id);
        map.put("character",character);
        likeMapper.plusPreferCount(map);
    }
    public void minusPrefer(String id, String character) {
        HashMap<String, Object> map = new HashMap();
        map.put("userId", id);
        map.put("character",character);
        likeMapper.minusPreferCount(map);
    }
}
