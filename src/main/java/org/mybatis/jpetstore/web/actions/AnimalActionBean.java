package org.mybatis.jpetstore.web.actions;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.configuration.AWSS3;
import org.mybatis.jpetstore.domain.AnimalMating;
import org.mybatis.jpetstore.service.AnimalService;
import org.mybatis.jpetstore.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SessionScope
public class AnimalActionBean extends AbstractActionBean {
    private static final long serialVersionUID = -6687732592582712578L;

    private static final String ADD_ANIMAL_MATING="/WEB-INF/jsp/animalmating/AddAnimalForm.jsp";
    private static final String LIST_ANIMAL_MATING="/WEB-INF/jsp/animalmating/ListAnimalMating.jsp";
    private static final String DETAIL_ANIMAL_MATING="/WEB-INF/jsp/animalmating/DetailAnimalMating.jsp";
    private static final List<String> CATEGORY_LIST;


    @SpringBean
    private transient AnimalService animalService;
    @SpringBean
    private transient CatalogService catalogService;

    static {
        CATEGORY_LIST = Collections.unmodifiableList(Arrays.asList("FISH", "DOGS", "REPTILES", "CATS", "BIRDS"));
    }

    public List<String> getCategories() {
        return CATEGORY_LIST;
    }

    private AnimalMating animalMating;

    private Logger logger = LoggerFactory.getLogger(AnimalActionBean.class);

    private FileBean fileBean;

    private List<AnimalMating> animalMatingList;
    private int page;
    private int id;

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getKeyword(){
        return  keyword;
    }

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    public List<AnimalMating> getAnimalMatingList() { return animalMatingList; }

    public void setAnimalMatingList(List<AnimalMating> animalMatingList) { this.animalMatingList = animalMatingList; }

    public AnimalMating getAnimalMating() { return animalMating; }

    public void setAnimalMating(AnimalMating animalMating) { this.animalMating = animalMating; }

    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    public FileBean getFileBean() {
        return fileBean;
    }

    private String keyword;


    @Autowired
    public AWSS3 awsS3 = AWSS3.getInstance();

    private String bucketName="jpet-img";



    public File convert(FileBean file) throws IOException {
        File convFile = new File(file.getFileName());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getInputStream().available());
        fos.close();
        return convFile;
    }

    // 파일 업로드 요청
    public Resolution uploadImg() throws Exception {
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String userId=accountBean.getUsername();
        String url=uploadImgFile();
        animalMating.setImgUrl(url);
        animalMating.setUserId(userId);
        animalService.insertAnimal(animalMating);
        animalMatingList = animalService.getAnimalMatingList();

        return new ForwardResolution(LIST_ANIMAL_MATING);
    }

    public Resolution addAnimalMatingView(){
        return new ForwardResolution(ADD_ANIMAL_MATING);
    }

    public Resolution listAnimalAccount(){
        animalMatingList = animalService.getAnimalMatingList();
        return new ForwardResolution(LIST_ANIMAL_MATING);
    }

    public Resolution getMatingInfo() {
        System.out.println("id = " + id);
        animalService.plusViewCount(id);
        animalMating = animalService.getAnimalMattingDetail(id);
        return new ForwardResolution(DETAIL_ANIMAL_MATING);
    }

    private String uploadImgFile() throws IOException {
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

    public ForwardResolution searchMating(){
        if (keyword == null || keyword.length() < 1) {
            setMessage("Please enter a keyword to search for, then press the search button.");
            return new ForwardResolution(ERROR);
        } else {
            animalMatingList = animalService.searchAnimalMatingList(keyword.toLowerCase());
            return new ForwardResolution(LIST_ANIMAL_MATING);
        }
    }



}
