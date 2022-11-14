package org.mybatis.jpetstore.web.actions;


import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.service.AdminService;

import java.util.List;

@SessionScope
public class AdminActionBean extends AbstractActionBean {

    private static final long serialVersionUID = -6687732592582712578L;


    private static final String M_PRODUCT = "/WEB-INF/jsp/management/ProductManagement.jsp";
    private static final String EDIT_PRODUCT = "/WEB-INF/jsp/management/EditItem.jsp";
    private static final String UPDATEITEMFORM = "/WEB-INF/jsp/management/UpdateItemForm.jsp";
    private static final String ADDITEMFORM = "/WEB-INF/jsp/management/AddItemForm.jsp";

    private String categoryId;
    private Category category;
    private List<Category> categoryList;

    private String productId;
    private Product product;
    private List<Product> productList;

    private String itemId;
    private Item item;
    private List<Item> itemList;

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
    private transient AdminService adminService;


    public ForwardResolution viewAllProduct() {
        productList = adminService.getAllProductList();

        return new ForwardResolution(M_PRODUCT);
    }

    public ForwardResolution editItem() {
        itemList = adminService.getItemListByProduct(productId);
        product = adminService.getProduct(productId);
        return new ForwardResolution(EDIT_PRODUCT);
    }

    public ForwardResolution updateItemView() {
        item = adminService.getItem(itemId);
        return new ForwardResolution(UPDATEITEMFORM);
    }

    public Resolution updateItem() {
        adminService.updateItem(item);
        return new ForwardResolution(EDIT_PRODUCT);
    }

    public Resolution addItemView() {
        product = adminService.getProduct(productId);
        return new ForwardResolution(ADDITEMFORM);
    }

    public Resolution addItem() {
        item.setProductId(productId);
        adminService.insertItem(item);
        itemList = adminService.getItemListByProduct(productId);
        product = adminService.getProduct(productId);
        return new ForwardResolution(EDIT_PRODUCT);
    }

    public Resolution deleteItem() {
        adminService.deleteItem(itemId);
        itemList = adminService.getItemListByProduct(productId);
        product = adminService.getProduct(productId);
        return new ForwardResolution(EDIT_PRODUCT);
    }
}

