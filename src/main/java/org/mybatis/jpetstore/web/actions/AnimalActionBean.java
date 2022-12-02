package org.mybatis.jpetstore.web.actions;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SessionScope
public class AnimalActionBean extends AbstractActionBean {
    private static final long serialVersionUID = -6687732592582712578L;

    private static final String ADD_ANIMAL_MATING="/WEB-INF/jsp/animalmating/AddAnimalForm.jsp";
    private static final String LIST_ANIMAL_MATING="/WEB-INF/jsp/animalmating/ListAnimalMating.jsp";
    private static final String LIST_RECOMMENDANIMAL_MATING="/WEB-INF/jsp/animalmating/RecommendMating.jsp";
    private static final String DETAIL_ANIMAL_MATING="/WEB-INF/jsp/animalmating/DetailAnimalMating.jsp";
    private static final String EDIT_ANIMAL_MATING="/WEB-INF/jsp/animalmating/EditAnimalForm.jsp";
    private static final String ALERT="/WEB-INF/jsp/animalmating/alert.jsp";
    private static final String WARNING = "/WEB-INF/jsp/animalmating/NotRecommend.jsp";
    private static final List<String> CATEGORY_LIST;
    private static final List<String> SEX_LIST;
    private static final List<String> CHARACTER_LIST;
    private static final List<String> STATUS_LIST;

    private static List<String> searchOptionList;
    private String searchOption;


    @SpringBean
    private transient AnimalService animalService;
    @SpringBean
    private transient CatalogService catalogService;

    static {
        CATEGORY_LIST = Collections.unmodifiableList(Arrays.asList("FISH", "DOGS", "REPTILES", "CATS", "BIRDS"));
        SEX_LIST = Collections.unmodifiableList(Arrays.asList("MALE","FEMALE"));
        CHARACTER_LIST=Collections.unmodifiableList(Arrays.asList("loving","friendly","playful","energetic","adventuresome","intelligent","loyal","timid","lazy","troublesome",
                "fierce","loud","kind","messy","shy","curious","cautious"));
        searchOptionList = Collections.unmodifiableList(Arrays.asList("Title", "Contents","UserName"));
        STATUS_LIST=Collections.unmodifiableList(Arrays.asList("RESERVED","COMPLETED"));
    }
    public List<String> getCharacters(){ return CHARACTER_LIST; }
    public List<String> getCategories() { return CATEGORY_LIST; }
    public List<String> getSex(){
        return SEX_LIST;
    }
    public List<String> getStatus(){
        return STATUS_LIST;
    }

    private AnimalMating animalMating;



    private AnimalMating animalMatingDetail;
    private List<String> animalMatingCha;


    private Logger logger = LoggerFactory.getLogger(AnimalActionBean.class);

    private FileBean fileBean;

    private List<AnimalMating> animalMatingList;

    private List<AnimalMating> recommendMatingList;

    private List<String> characterList;



    public static final int PAGESIZE = 10;
    private String username; //로그인한 유저
    private int id;
    private int cpage;
    private int psStr;
    private int pageCount;
    private int postCount;
    private int preBlock;
    private int nextBlock;
    //개인게시물 수정,삭제 버튼
    private int btnUpdate;
    private int btnDelete;
    private String matingStatusValue;

    private String keyword;
    private String chooseWork;
    private String code;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public void setBtnDelete(int btnDelete){this.btnDelete=btnDelete;}
    public void setBtnUpdate(int btnUpdate){this.btnUpdate=btnUpdate;}

    //테스트
    public int getCpage() { return cpage; }
    public void setCpage(int cpage) { this.cpage = cpage; }

    public int getPsStr() { return psStr; }
    public void setPsStr(int psStr) { this.psStr = psStr; }

    public int getPostCount() { return postCount; }
    public void setPostCount(int postCount) { this.postCount = postCount; }

    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public int getPreBlock() { return preBlock; }
    public void setPreBlock(int preBlock) { this.preBlock = preBlock; }

    public int getNextBlock() { return nextBlock; }
    public void setNextBlock(int nextBlock) { this.nextBlock = nextBlock; }

    public List<AnimalMating> getAnimalMatingList() { return animalMatingList; }
    public void setAnimalMatingList(List<AnimalMating> animalMatingList) { this.animalMatingList = animalMatingList; }

    public AnimalMating getAnimalMating() { return animalMating; }
    public void setAnimalMating(AnimalMating animalMating) { this.animalMating = animalMating; }

    public AnimalMating getAnimalMatingDetail() {
        return animalMatingDetail;
    }

    public void setAnimalMatingDetail(AnimalMating animalMatingDetail) {
        this.animalMatingDetail = animalMatingDetail;
    }
    public List<String> getAnimalMatingCha() {
        return animalMatingCha;
    }
    public void setAnimalMatingCha (List<String> animalMatingCha) {
        this.animalMatingCha = animalMatingCha;
    }

    public void setFileBean(FileBean fileBean) { this.fileBean = fileBean; }
    public FileBean getFileBean() { return fileBean; }

    public String getMatingStatusValue() { return matingStatusValue; }
    public void setMatingStatusValue(String matingStatusValue) { this.matingStatusValue = matingStatusValue; }



    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<String> getSearchOptionList() { return searchOptionList; }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }
    public String getSearchOption() { return searchOption; }

    public void setChooseWork(String chooseWork){this.chooseWork = chooseWork;}
    public String getChooseWork(){return chooseWork;}

    public List<String> getCharacterList() { return characterList; }
    public void setCharacterList(List<String> characterList) { this.characterList = characterList; }

    public List<AnimalMating> getRecommendMatingList() { return recommendMatingList; }
    public void setRecommendMatingList(List<AnimalMating> recommendMatingList) { this.recommendMatingList = recommendMatingList; }

    @Autowired
    public AWSS3 awsS3 = AWSS3.getInstance();

    public ForwardResolution updateStatus() {
        animalService.updateStatus(id, matingStatusValue);
        return new ForwardResolution(ALERT);
    }

    // 파일 업로드 요청
    public Resolution uploadImg() throws Exception {
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String userId=accountBean.getUsername();


        if(fileBean==null&&chooseWork.equals("add")){
            setMessage("PLEASE POST IMG FILE");
            return new ForwardResolution(ERROR);
        }else if(fileBean==null&&chooseWork.equals("edit")){
            animalMating.setUserId(userId);
        }
        else if(animalMating.getTitle()==null||animalMating.getCharacters()==null||animalMating.getContents()==null||
                animalMating.getSex()==null||animalMating.getCharacterList().isEmpty()){
            setMessage("내용을 모두 입력해주세요");
            return new ForwardResolution(ERROR);
        }else{
            String url=animalService.uploadImgFile(fileBean);
            animalMating.setImgUrl(url);
            animalMating.setUserId(userId);
        }



        if(getChooseWork().equals("add")){
            int id = animalService.insertAnimal(animalMating);
            animalService.addCharacter(id,animalMating.getCharacterList());
        }else{
            int id = animalService.editAnimal(animalMating);
            animalService.editCharacter(id,animalMating.getCharacterList());
            animalService.deleteOldCharacter(id,animalMating.getCharacterList());
            getMatingInfo();
            return new ForwardResolution(DETAIL_ANIMAL_MATING);
        }
        int temp = getPagingEnd(1, searchOption, keyword, code);


        int start = getPagingStart(temp);
        animalMatingList = animalService.searchAnimal(start, PAGESIZE, searchOption, keyword);

        clear();
        return new ForwardResolution(LIST_ANIMAL_MATING);
    }

    public Resolution addAnimalMatingView(){
        setChooseWork("add");
        clear();
        return new ForwardResolution(ADD_ANIMAL_MATING);
    }

    public Resolution editAnimalMatingView(){
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String userId=accountBean.getUsername();
        animalMating = animalService.getAnimalMattingDetail(id,userId);
        animalMating.setCharacterList(animalService.getCharacterList(id));
        setChooseWork("edit");
        return new ForwardResolution(EDIT_ANIMAL_MATING);
    }


    public Resolution listAnimalAccount(){

        cpage = 1;
        keyword = null;
        int temp = getPagingEnd(cpage, searchOption, keyword, code);
        int start = getPagingStart(temp);

        animalMatingList = animalService.searchAnimal(start, PAGESIZE, searchOption, keyword);

        return new ForwardResolution(LIST_ANIMAL_MATING);
    }

    public Resolution recommendAnimalMating(){
        cpage = 1;
        keyword = null;
        int temp = getPagingEnd(cpage, searchOption, keyword, code);
        // TO DO : 처음에 보여줄 추천게시물 없을때 처리
        int start = getPagingStart(temp);

        animalMatingList = animalService.searchRecommendAnimal(username, start, PAGESIZE, searchOption, keyword);
        return new ForwardResolution(LIST_ANIMAL_MATING);
    }


    public Resolution getMatingInfo() {
        animalService.plusViewCount(id);
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String userId=accountBean.getUsername();
        System.out.println(userId);
        animalMatingDetail = animalService.getAnimalMattingDetail(id,userId);
        animalMatingCha = animalService.getAnimalMatingCha(id);
        return new ForwardResolution(DETAIL_ANIMAL_MATING);
    }


    private int getPagingEnd(int cpage, String searchOption, String keyword, String code) {
        this.cpage = cpage;

        psStr = PAGESIZE;
        if(searchOption == null) {
            searchOption = "all";
        }
        if(code.equals("all"))
            postCount = animalService.getCount(searchOption, keyword);
        else if(code.equals("recomm")) {
            postCount = animalService.getRecommendCount(username, searchOption, keyword);
            // TO DO : 처음에 보여줄 추천게시물 없을때 처리
        }


        pageCount = (postCount - 1) / PAGESIZE + 1;
        if(cpage < 1)
            cpage = 1;
        else if(cpage > pageCount)
            cpage = pageCount;

        preBlock = (cpage -1)/PAGESIZE * PAGESIZE;
        nextBlock = preBlock + PAGESIZE + 1;

        return PAGESIZE* cpage;

    }

    private int getPagingStart(int end) {
        return end - PAGESIZE + 1;
    }

    public Resolution paging() {
        int temp = getPagingEnd(cpage, searchOption, keyword, code);
        int start = getPagingStart(temp);
        if(code.equals("all"))
            animalMatingList = animalService.searchAnimal(start, PAGESIZE, searchOption, keyword);
        else if(code.equals("recomm"))
            animalMatingList = animalService.searchRecommendAnimal(username, start, PAGESIZE, searchOption, keyword);

            return new ForwardResolution(LIST_ANIMAL_MATING);
    }

    public ForwardResolution searchMating() {
        cpage = 1;
        int temp = getPagingEnd(cpage, searchOption, keyword, code);
        System.out.println("code : " + code);
        int start = getPagingStart(temp);
        if(code.equals("all"))
            animalMatingList = animalService.searchAnimal(start, PAGESIZE, searchOption, keyword);
        else if(code.equals("recomm"))
            animalMatingList = animalService.searchRecommendAnimal(username, start, PAGESIZE, searchOption, keyword);

        return new ForwardResolution(LIST_ANIMAL_MATING);
    }



    public ForwardResolution userDeleteBtnSet(){
        System.out.println("Delete id = "+btnDelete);
        animalService.userAnimalDelete(id);
        listAnimalAccount();
        return new ForwardResolution("/WEB-INF/jsp/animalmating/ListAnimalMating.jsp");
    }

    public void clear(){
        animalMating=new AnimalMating();
    }



}
