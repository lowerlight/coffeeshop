package com.coffeeshop.mappers;

import com.coffeeshop.core.Menu;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * *************************************************************************************************
 * <p/>
 * Project : Coffeeshop
 * Filename : MenuMapper.java
 * <p/>
 * *************************************************************************************************
 */

public class MenuMapper implements ResultSetMapper<Menu> {
    public Menu map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        return new Menu(rs.getInt("id"), rs.getString("name"), rs.getInt("costInCents"));
    }
}
