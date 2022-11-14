package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.mapper.CategoryMapper;
import org.mybatis.jpetstore.mapper.ItemMapper;
import org.mybatis.jpetstore.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final CategoryMapper categoryMapper;
    private final ItemMapper itemMapper;
    private final ProductMapper productMapper;

    public AdminService(CategoryMapper categoryMapper, ItemMapper itemMapper, ProductMapper productMapper) {
        this.categoryMapper = categoryMapper;
        this.itemMapper = itemMapper;
        this.productMapper = productMapper;
    }

    public Product getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    public List<Product> getAllProductList() {
        return productMapper.getAllProductList();
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemMapper.getItemListByProduct(productId);
    }
    public Item getItem(String itemId) {
        return itemMapper.getItem(itemId);
    }

    public void updateItem(Item item) {
        Map<String, Object> params = new HashMap<>();
        params.put("attr1", item.getAttribute1());
        params.put("listPrice", item.getListPrice());
        params.put("itemId", item.getItemId());
        params.put("quantity", item.getQuantity());
        itemMapper.updateByattr1AndQuantity(params);
        itemMapper.updateQuantity(params);
    }
}
