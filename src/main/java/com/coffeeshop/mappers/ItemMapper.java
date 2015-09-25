package com.coffeeshop.mappers;

import com.coffeeshop.core.Item;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : ItemMapper.java
 * <p/>
 * *************************************************************************************************
 */

public class ItemMapper implements ResultSetMapper<Item> {
    public Item map(int index, ResultSet rs, StatementContext ctx) throws SQLException{
        return new Item(rs.getInt("id"), rs.getString("name"), rs.getInt("costInCents"),
                rs.getInt("menuId"));
    }
}
