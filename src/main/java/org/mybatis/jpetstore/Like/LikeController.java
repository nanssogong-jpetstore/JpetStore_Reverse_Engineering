package org.mybatis.jpetstore.Like;

import org.mybatis.jpetstore.Chat.ChatController;
import org.mybatis.jpetstore.Chat.ChatService;
import org.mybatis.jpetstore.domain.BoardLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LikeController {

    private final LikeService likeService;
    private List<String> animalCha;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    private Logger logger = LoggerFactory.getLogger(LikeController.class);
    @PostMapping("/like")
    public Map<String, String> likeBoard(@RequestBody BoardLike boardLike){
        logger.info(boardLike.getUserId());
        Map<String,String> map = new HashMap<String, String>();
        try{
            //해당 게시글 성격 리스트로 가져오기
            animalCha=likeService.getAnimalCha(boardLike.getBoardId());
            String uID = boardLike.getUserId();

            if(likeService.checkLike(boardLike)==1){
                likeService.unLike(boardLike);
                //좋아요 취소하면 성격 선호도 감소
                for (int i = 0; i < animalCha.size(); i++) {
                    likeService.minusPrefer(uID, animalCha.get(i));
                }
            }
            else{
                likeService.Like(boardLike);
                //좋아요 클릭시 성격 선호도 증가
                for (int i = 0; i < animalCha.size(); i++) {
                    likeService.plusPrefer(uID, animalCha.get(i));
                }
            }
            map.put("result","success");
        }catch(Exception e){
            e.printStackTrace();
            map.put("result","fail");
        }
        return map;
    }

    @PostMapping("/likeCheck")
    public Map<String, String> likeCheck(@RequestBody BoardLike boardLike){
        Map<String, String> map = new HashMap<String, String>();
        try {
            logger.info("get");
            map.put("result", "success");
            map.put("likeCheck", String.valueOf(likeService.checkLike(boardLike)));
        }catch (Exception e){
            logger.info("fail");
            e.printStackTrace();
            map.put("result","fail");
            map.put("likeCheck","2");
        }
        return map;
    }
}
