package com.coffeeshop.resources;

import com.codahale.metrics.annotation.Timed;
import com.coffeeshop.core.CoffeeMenu;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : CoffeeMenuResource.java
 * <p/>
 * *************************************************************************************************
 */

@Path("/coffeemenu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeMenuResource {
    private final MenuDAO menuDao;
    private final String template;
    private final String defaultName;
    //private final AtomicLong counter;

    public CoffeeMenuResource(DBI jdbi, String template, String defaultName) {
        this.menuDao = jdbi.onDemand(MenuDAO.class);
        this.template = template;
        this.defaultName = defaultName;
        //this.counter = new AtomicLong();
    }

    public MenuDAO getMenuDao(){
        return this.menuDao;
    }

    //#index
    @GET
    @Timed
    @Path("/menus.json")
    public List<CoffeeMenu> findAllMenu() {
        // retrieve all menu
        List<CoffeeMenu> menuList = menuDao.findAllMenu();
        if (menuList != null) {
            return menuList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //#show
    @GET
    @Timed
    @Path("/menus.json/{id}")
    public CoffeeMenu findMenu(@PathParam("id") int id) {
        // retrieve information about the drink with the provided id
        CoffeeMenu menu = menuDao.findMenuById(id);
        if (menu != null) {
            return menu;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
