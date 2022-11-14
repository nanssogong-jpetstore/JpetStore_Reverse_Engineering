package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Inventory;

public interface InventoryMapper {

    void insertInventory(Inventory inventory);

    void deleteInventory(String itemId);
}
