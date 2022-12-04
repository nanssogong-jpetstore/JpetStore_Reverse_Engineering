package org.mybatis.jpetstore.web.actions;


import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.CatalogService;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionScope
public class AdminActionBean extends AbstractActionBean {

    private static final long serialVersionUID = -6687732592582712578L;


    private static final String M_PRODUCT = "/WEB-INF/jsp/management/ProductManagement.jsp";
    private static final String EDIT_PRODUCT = "/WEB-INF/jsp/management/EditItem.jsp";
    private static final String UPDATEITEMFORM = "/WEB-INF/jsp/management/UpdateItemForm.jsp";
    private static final String ADDITEMFORM = "/WEB-INF/jsp/management/AddItemForm.jsp";
    private static final String MANAGEMENTALERT = "/WEB-INF/jsp/management/ManagementAlert.jsp";

    private String username;
    private String categoryId;
    private Category category;
    private List<Category> categoryList;

    private String productId;
    private Product product;
    private List<Product> productList;

    private String itemId;
    private Item item;
    private List<Item> itemList;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }


    @SpringBean
    private transient CatalogService catalogService;

    @SpringBean
    private transient AccountService accountService;


    public AdminActionBean() {
        System.out.println("Admin Action Bean Start");
    }

    public ForwardResolution viewAllProduct() {
        if(isAdmin(username)) {
            productList = catalogService.getAllProductList();
            return new ForwardResolution(M_PRODUCT);
        }
        return new ForwardResolution(MANAGEMENTALERT);
    }

    public ForwardResolution editItem() {
        if(isAdmin(username)) {
            itemList = catalogService.getItemListByProduct(productId);
            product = catalogService.getProduct(productId);
            return new ForwardResolution(EDIT_PRODUCT);
        }
        return new ForwardResolution(MANAGEMENTALERT);
    }

    public ForwardResolution updateItemView() {
        if(isAdmin(username)) {
            item = catalogService.getItem(itemId);
            return new ForwardResolution(UPDATEITEMFORM);
        }
        return new ForwardResolution(MANAGEMENTALERT);
    }

    public Resolution updateItem() {
        if(isAdmin(username)) {
            catalogService.updateItem(item);
            itemList = catalogService.getItemListByProduct(productId);
            return new ForwardResolution(EDIT_PRODUCT);
        }
        return new ForwardResolution(MANAGEMENTALERT);
    }

    public Resolution addItemView() {
        if(isAdmin(username)) {
            product = catalogService.getProduct(productId);
            return new ForwardResolution(ADDITEMFORM);
        }
        return new ForwardResolution(MANAGEMENTALERT);
    }

    public Resolution addItem() {
        if(isAdmin(username)) {
            item.setProductId(productId);
            catalogService.insertItem(item);
            itemList = catalogService.getItemListByProduct(productId);
            product = catalogService.getProduct(productId);
            return new ForwardResolution(EDIT_PRODUCT);
        }
        return new ForwardResolution(MANAGEMENTALERT);
    }

    public Resolution deleteItem() {
        if(isAdmin(username)) {
            catalogService.deleteItem(itemId);
            itemList = catalogService.getItemListByProduct(productId);
            product = catalogService.getProduct(productId);
            return new ForwardResolution(EDIT_PRODUCT);
        }
        return new ForwardResolution(MANAGEMENTALERT);
    }

    private boolean isAdmin(String username) {
        HttpSession httpSession = context.getRequest().getSession();
        AccountActionBean accountActionBean = (AccountActionBean) httpSession.getAttribute("accountBean");

        return accountService.getAccount(accountActionBean.getUsername()).getRank().equals("MANAGEMENT");
    }
}

