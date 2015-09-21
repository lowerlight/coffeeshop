package com.coffeeshop.mappers;

import com.coffeeshop.core.Coffeeshop;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : CoffeeshopMapper.java
 * <p/>
 * *************************************************************************************************
 */

public class CoffeeshopMapper implements ResultSetMapper<Coffeeshop> {
    public Coffeeshop map(int index, ResultSet rs, StatementContext ctx) throws SQLException{
        return new Coffeeshop (rs.getInt("id"), rs.getString("name"), rs.getInt("costInCents"));
    }
}
