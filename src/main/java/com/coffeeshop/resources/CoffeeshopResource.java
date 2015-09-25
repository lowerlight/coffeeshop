package com.coffeeshop.resources;

import com.codahale.metrics.annotation.Timed;
import com.coffeeshop.core.Menu;
import com.coffeeshop.core.Item;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : CoffeeshopResource.java
 * <p/>
 * *************************************************************************************************
 */

@Path("/coffeeshop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeshopResource //implements Switch
{
    private final CoffeeshopDAO coffeeshopDao;
    private final String template;
    private final String defaultName;
    //private final AtomicLong counter;

    public CoffeeshopResource(DBI jdbi, String template, String defaultName) {
        this.coffeeshopDao = jdbi.onDemand(CoffeeshopDAO.class);
        this.template = template;
        this.defaultName = defaultName;
        //this.counter = new AtomicLong();
    }

    //Sample usage:
    //final int cost = 0;
    //final String value = String.format(template, defaultName);
    //return new Item(((int) counter.incrementAndGet()), value, cost);

    //#index
    @GET
    @Timed
    @Path("/items.json")
    public List<Item> findAllOrder() {
        // retrieve all order items
        List<Item> itemList = coffeeshopDao.findAllItem();
        if (itemList != null) {
            return itemList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //#create
    @POST
    @Timed
    @Path("/items/new/{menuId}.json")
    public Response createItem(@PathParam("menuId") int menuId)
    {
        Menu menu = coffeeshopDao.prepareItemFromMenuId(menuId);
        Item item = new Item(menu.getId(), menu.getName(),
                            menu.getCostInCents(), menuId);

        try {
            coffeeshopDao.updateItem(item.getName(), item.getCostInCents(), item.getMenuId());
            System.err.println("Added order item " + item.getName() + " " + item.getCostInCents());
            return Response.status(Response.Status.CREATED).build();
            //return Response.noContent().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
            //return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }

        //TODO Optimise SQL of updating and displaying all orders back at the same time
    }

    //#show
    @GET
    @Timed
    @Path("/items.json/{id}")
    public Item findOrder(@PathParam("id") int id) {
        // retrieve information about the drink with the provided id
        Item item = coffeeshopDao.findItemById(id);
        if (item != null) {
            return item;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //#destroy
    @DELETE
    @Timed
    @Path("/items.json/{id}")
    public Response cancelItem(@PathParam("id") int id){

        //TODO How to delete based on id only in a non-Item JSON object ?
        System.err.println(id);
        try{
            coffeeshopDao.removeItem(id);
            return Response.noContent().build();
        }
        catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
            //return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    //#index
    @GET
    @Timed
    @Path("/menus.json")
    public List<Menu> findAllMenu() {
        // retrieve all menu
        List<Menu> menuList = coffeeshopDao.findAllMenu();
        if (menuList != null) {
            return menuList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //#show
    @GET
    @Timed
    @Path("/menus.json/{id}")
    public Menu findMenu(@PathParam("id") int id) {
        // retrieve information about the drink with the provided id
        Menu menu = coffeeshopDao.findMenuById(id);
        if (menu != null) {
            return menu;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //Not supported yet=============================================================================

    /*
    @GET
    @Timed
    @Path("/items.json/{userPattern}")
    public List<Item> findGroupedItem(@PathParam("userPattern") String userPattern) {
        System.err.println(userPattern);
        // retrieve grouped items
        List<Item> itemList = coffeeshopDao.findItemByName("%" + userPattern + "%");
        if (itemList != null) {
            return itemList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @POST
    @Timed
    @Path("/cancel_group_item/{userPattern}")
    public Response cancelGroupItem(@PathParam("userPattern") String userPattern){

        //TODO How to delete based on userPattern in a non-Item JSON object ?
        System.err.println(userPattern);
        try{
            coffeeshopDao.removeGroupItemByName("%" + userPattern + "%");
            return Response.noContent().build();
        }
        catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
            //return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
    */
}
