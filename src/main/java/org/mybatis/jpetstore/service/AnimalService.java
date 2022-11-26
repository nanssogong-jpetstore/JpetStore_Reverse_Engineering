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
import java.util.List;
import java.util.UUID;


@Service
public class AnimalService {

    private final AnimalMapper animalMapper;

    public AnimalService(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }



    public void insertAnimal(AnimalMating animalMating) {

        System.out.println(animalMating.getImgUrl());
        animalMapper.insertAnimal(animalMating);

    }

    public List<AnimalMating> getAnimalMatingList() {
        return animalMapper.getAnimalMatingList();
    }


    public AWSS3 awsS3 = AWSS3.getInstance();

    private String bucketName="jpet-img";

    private FileBean fileBean;
    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    public FileBean getFileBean() {
        return fileBean;
    }
    private Logger logger = LoggerFactory.getLogger(AnimalActionBean.class);


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

}
