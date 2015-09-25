package com.coffeeshop.resources;

import com.coffeeshop.core.CoffeeMenu;
import com.coffeeshop.mappers.CoffeeMenuMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : MenuDAO.java
 * <p/>
 * *************************************************************************************************
 */
public interface MenuDAO {
    @SqlUpdate("CREATE TABLE IF NOT EXISTS menus" +
            "(id INTEGER PRIMARY KEY, name TEXT, costInCents INTEGER)")
    void createMenuTable();

    //#index
    @Mapper(CoffeeMenuMapper.class)
    @SqlQuery("SELECT * FROM menus")
    List<CoffeeMenu> findAllMenu();

    //#show
    @Mapper(CoffeeMenuMapper.class)
    @SqlQuery("SELECT * FROM menus WHERE id = :id")
    CoffeeMenu findMenuById(@Bind("id") int id);
}
