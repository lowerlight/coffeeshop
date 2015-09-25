package com.coffeeshop.mappers;

import com.coffeeshop.core.CoffeeMenu;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : CoffeeMenuMapper.java
 * <p/>
 * *************************************************************************************************
 */

public class CoffeeMenuMapper implements ResultSetMapper<CoffeeMenu> {
    public CoffeeMenu map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        return new CoffeeMenu (rs.getInt("id"), rs.getString("name"), rs.getInt("costInCents"));
    }
}
