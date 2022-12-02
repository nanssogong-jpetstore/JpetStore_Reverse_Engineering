package org.mybatis.jpetstore.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import net.sourceforge.stripes.action.FileBean;
import org.mybatis.jpetstore.configuration.AWSS3;
import org.mybatis.jpetstore.domain.AnimalMating;
import org.mybatis.jpetstore.mapper.AnimalMapper;
import org.mybatis.jpetstore.web.actions.AnimalActionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mybatis.jpetstore.configuration.Constants.BUCKET;


@Service
public class AnimalService {

    private final AnimalMapper animalMapper;

    public AnimalService(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }



    public int insertAnimal(AnimalMating animalMating) {
        animalMapper.insertAnimal(animalMating);
        return animalMating.getId();
    }

    public void editAnimal(AnimalMating animalMating){
        animalMapper.editAnimal(animalMating);

    }

    public List<AnimalMating> getAnimalMatingList(int start, int end) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start - 1);
        condition.put("end", end);
        return animalMapper.getAnimalMatingList(condition);
    }

    public List<AnimalMating> getRecommendAnimalList(String id, int start, int end){
        Map<String, Object> condition = new HashMap<>();
        condition.put("id", id);
        condition.put("start", start - 1);
        condition.put("end", end);
        return animalMapper.getRecommendAnimalList(condition);
    }
    public AnimalMating getAnimalMattingDetail(int id) { return animalMapper.getAnimalMattingDetail(id); }

    public AWSS3 awsS3 = AWSS3.getInstance();

    private String bucketName=BUCKET;

    private FileBean fileBean;
    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    public FileBean getFileBean() {
        return fileBean;
    }
    private Logger logger = LoggerFactory.getLogger(AnimalActionBean.class);


    public int getCount(String keyword) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("value", "%" + keyword + "%");
        if(keyword.equals(null)) {
            return animalMapper.getAnimalMatingCount();
        }else if(keyword.equals("UserName")) {
            return animalMapper.searchAnimalMatingUserCount(condition);
        }else if(keyword.equals("Title")) {
            return animalMapper.searchAnimalMatingTitleCount(condition);
        }else if(keyword.equals("Contents")) {
            return animalMapper.searchAnimalMatingContentsCount(condition);
        }
        return animalMapper.getAnimalMatingCount();
    }

    public int getRecommendCount(String id, String keyword) {
        System.out.println("keyword ASFASFSAF : " + id);
        System.out.println("keyword ASFASFSAF : " + keyword);
        Map<String, Object> condition = new HashMap<>();
        condition.put("id", id);
        condition.put("value", "%" + keyword + "%");
        if(keyword.equals(null)) {
            return animalMapper.getRecommendMatingCount(id);
        } else if(keyword.equals("UserName")) {
            return animalMapper.searchRecommendAnimalUserCount(condition);
        }else if(keyword.equals("Title")) {
            return animalMapper.searchRecommendAnimalTitleCount(condition);
        }else if(keyword.equals("Contents")) {
            return animalMapper.searchRecommendAnimalContentsCount(condition);
        }
        return animalMapper.getRecommendMatingCount(id);
    }

    public String uploadImgFile(FileBean fileBean) throws IOException {
        try {
            System.out.println(fileBean.getFileName());
            String fName = fileBean.getFileName();
            System.out.println(fName.indexOf("."));

            if (fName.indexOf(".") != -1) {
                String ext = fName.split("\\.")[1];
                String contentType="";
                switch (ext) {
                    case "jpeg":
                        contentType = "image/jpeg";
                        break;
                    case "png":
                        contentType = "image/png";
                        break;
                    case "txt":
                        contentType = "text/plain";
                        break;
                    case "csv":
                        contentType = "text/csv";
                        break;
                    case "jpg":
                        contentType = "image/jpg";
                        break;
                }

                ObjectMetadata metadata=new ObjectMetadata();
                metadata.setContentType(contentType);
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, UUID.randomUUID() + "." + ext, fileBean.getInputStream(),metadata);
                putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
                awsS3.uploadToS3(putObjectRequest);
                logger.info("===================== Upload File - Done! =====================");
                return "https://jpet-img.s3.ap-northeast-2.amazonaws.com/"+putObjectRequest.getKey();

            }
        } catch (AmazonServiceException ase) {
            logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
        }
        return null;
    }

    public void plusViewCount(int id) {
        animalMapper.plusViewCount(id);
    }

    //제목기준 검색
    public List<AnimalMating> searchAnimalMatingTitle(int start, int end, String keywords){
        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start - 1);
        condition.put("end", end);
        condition.put("value", "%" + keywords + "%");

        return animalMapper.searchAnimalMatingTitle(condition);
    }
    //내용기준 검색
    public List<AnimalMating> searchAnimalMatingContents(int start, int end, String keywords){
        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start - 1);
        condition.put("end", end);
        condition.put("value", "%" + keywords + "%");


        return animalMapper.searchAnimalMatingContents(condition);
    }
    //유저이름으로 검색
    public List<AnimalMating> searchAnimalMatingUser(int start, int end, String keywords){
        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start - 1);
        condition.put("end", end);
        condition.put("value", "%" + keywords + "%");
        List<AnimalMating> animalMatings = new ArrayList<>();

        return animalMapper.searchAnimalMatingUser(condition);
    }
    //추천제목기준 검색
    public List<AnimalMating> searchRecommendAnimalTitle(int start, int end, String keywords){
        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start - 1);
        condition.put("end", end);
        condition.put("value", "%" + keywords + "%");

        return animalMapper.searchRecommendAnimalTitle(condition);
    }
    //추천내용기준 검색
    public List<AnimalMating> searchRecommendAnimalContents(int start, int end, String keywords){
        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start - 1);
        condition.put("end", end);
        condition.put("value", "%" + keywords + "%");


        return animalMapper.searchRecommendAnimalContents(condition);
    }
    //추천유저이름으로 검색
    public List<AnimalMating> searchRecommendAnimalUser(int start, int end, String keywords){
        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start - 1);
        condition.put("end", end);
        condition.put("value", "%" + keywords + "%");
        List<AnimalMating> animalMatings = new ArrayList<>();

        return animalMapper.searchRecommendAnimalUser(condition);
    }

    public void addCharacter(int id, List<String> animalCharacters) {
        Map<String,Object> animalCharacter = new HashMap<>();
        for (int i=0;i<animalCharacters.size();i++) {
            animalCharacter.put("id",id);
            animalCharacter.put("character",animalCharacters.get(i));
            animalMapper.addCharacter(animalCharacter);
        }
    }
}
