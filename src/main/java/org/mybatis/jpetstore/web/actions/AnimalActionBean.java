package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.AnimalMating;
import org.mybatis.jpetstore.service.AnimalService;
import org.mybatis.jpetstore.service.CatalogService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SessionScope
public class AnimalActionBean extends AbstractActionBean {
    private static final long serialVersionUID = -6687732592582712578L;

    private static final String ADD_ANIMAL_MATING="/WEB-INF/jsp/animalmating/AddAnimalForm.jsp";
    private static final String LIST_ANIMAL_MATING="/WEB-INF/jsp/animalmating/ListAnimalMating.jsp";

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


    private FileBean fileBean;

    private List<AnimalMating> animalMatingList;
    private int page;
    private int id;

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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


    // 파일 업로드 요청
    public Resolution uploadImg() throws Exception {
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        String userId=accountBean.getUsername();

        if(fileBean==null){
            setMessage("PLEASE POST IMG FILE");
            return new ForwardResolution(ERROR);
        }
        if(animalMating.getTitle()==null){
            setMessage("PLEASE ENTER A TITLE");
            return new ForwardResolution(ERROR);
        }
        if(animalMating.getCharacters()==null){
            setMessage("PLEASE ENTER ANIMAL CHARACTERS");
            return new ForwardResolution(ERROR);
        }
        if(animalMating.getContents()==null){
            setMessage("PLEASE ENTER CONTENTS");
            return new ForwardResolution(ERROR);
        }

        String url=animalService.uploadImgFile(fileBean);
        if(url==null){
            setMessage("IMG 업로드에 실패하였습니다.");
            return new ForwardResolution(ERROR);
        }

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
        /*
        * To. 승현
        * id값 나오니까 이걸로 상세조회 where id 조건 주면 됨.
        */
        return new ForwardResolution("상세 페이지 이동");
    }


}
