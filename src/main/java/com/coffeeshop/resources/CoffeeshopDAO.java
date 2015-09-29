package com.coffeeshop.resources;

import com.coffeeshop.core.Menu;
import com.coffeeshop.core.Item;
import com.coffeeshop.mappers.MenuMapper;
import com.coffeeshop.mappers.ItemMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * *************************************************************************************************
 * <p/>
 * Project : Coffeeshop
 * Filename : CoffeeshopDAO.java
 * <p/>
 * *************************************************************************************************
 */
public interface CoffeeshopDAO {
    //item#index
    @Mapper(ItemMapper.class)
    @SqlQuery("SELECT * FROM item")
    List<Item> findAllItem();

    //item#create
    @Mapper(MenuMapper.class)
    @SqlQuery("SELECT * FROM menus WHERE id = :menuId")
    Menu prepareItemFromMenuId(@Bind("menuId") int menuId);
    @SqlUpdate("INSERT INTO item (name, costInCents, menuId) VALUES (:name, :costInCents, :menuId)")
    void updateItem(@Bind("name") String name, @Bind("costInCents") int costInCents,
                    @Bind("menuId") int menuId);

    //menu#index
    @Mapper(MenuMapper.class)
    @SqlQuery("SELECT * FROM menus")
    List<Menu> findAllMenu();

    //Not integrated yet, for demo only ============================================================

    //item#destroy
    @Mapper(ItemMapper.class)
    @SqlQuery("SELECT * FROM item WHERE id = :id")
    Item findItemById(@Bind("id") int id);
    @SqlUpdate("DELETE FROM item WHERE id = :id")
    void removeItem(@Bind("id") int id);

    //Create menu (not for customer use)
    @SqlUpdate("CREATE TABLE IF NOT EXISTS menus" +
            "(id INTEGER PRIMARY KEY, name TEXT, costInCents INTEGER)")
    void createMenuTable();

    //Initial order item (not for customer use)
    @SqlUpdate("CREATE TABLE IF NOT EXISTS item" +
            "(id INTEGER PRIMARY KEY, name TEXT, costInCents INTEGER, menuId INTEGER)")
    void createItemTable();

    //Not supported yet=============================================================================

    //menu#show
    @Mapper(MenuMapper.class)
    @SqlQuery("SELECT * FROM menus WHERE id = :id")
    Menu findMenuById(@Bind("id") int id);

    @Mapper(ItemMapper.class)
    @SqlQuery("SELECT * FROM item WHERE name LIKE :pattern")
    List<Item> findItemByName(@Bind("pattern") String pattern);

    //Remove Items in group
    @SqlUpdate("DELETE FROM item WHERE name LIKE :pattern")
    void removeGroupItemByName(@Bind("pattern") String pattern);
}
