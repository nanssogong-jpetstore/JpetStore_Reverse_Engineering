package org.mybatis.jpetstore.domain;

import org.mybatis.jpetstore.mapper.InventoryMapper;

import java.io.Serializable;

public class Inventory implements Serializable {
    private static final long serialVersionUID = -8873387298725259763L;

    private String itemId;
    private int qty;

    public Inventory(String itemId, int qty) {
        this.itemId = itemId;
        this.qty = qty;
    }
    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }

    public int getQty() { return qty; }

    public void setQty(int qty) { this.qty = qty; }

    @Override
    public String toString() {
        return "(" + getItemId() + "-" + getQty() + ")";
    }
}
