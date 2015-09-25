package com.coffeeshop.resources;

import com.coffeeshop.core.Coffeeshop;
import com.coffeeshop.mappers.CoffeeshopMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : ItemDAO.java
 * <p/>
 * *************************************************************************************************
 */
public interface ItemDAO {
    @SqlUpdate("CREATE TABLE IF NOT EXISTS item" +
            "(id INTEGER PRIMARY KEY, name TEXT, costInCents INTEGER)")
    void createItemTable();

    //#index
    @Mapper(CoffeeshopMapper.class)
    @SqlQuery("SELECT * FROM item")
    List<Coffeeshop> findAllItem();

    //new



    //#show
    @Mapper(CoffeeshopMapper.class)
    @SqlQuery("SELECT * FROM item WHERE id = :id")
    Coffeeshop findItemById(@Bind("id") int id);

    //#create
    @SqlUpdate("INSERT INTO item (name, costInCents) VALUES (:name, :costInCents)")
    void updateItem(@Bind("name") String name, @Bind("costInCents") int costInCents);


    //Not supported yet=============================================================================

    @Mapper(CoffeeshopMapper.class)
    @SqlQuery("SELECT * FROM item WHERE name LIKE :pattern")
    List<Coffeeshop> findItemByName(@Bind("pattern") String pattern);

    //TODO Decide whether id should come from backend or frontend
    //Remove Items individually
    @SqlUpdate("DELETE FROM item WHERE id = :id")
    void removeItem(@Bind("id") int id);

    //Remove Items in group
    @SqlUpdate("DELETE FROM item WHERE name LIKE :pattern")
    void removeGroupItemByName(@Bind("pattern") String pattern);
}
