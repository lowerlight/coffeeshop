package com.coffeeshop.resources;

import com.codahale.metrics.annotation.Timed;
import com.coffeeshop.core.CoffeeMenu;
import com.coffeeshop.core.Coffeeshop;
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

    private final ItemDAO itemDao;
    private final String template;
    private final String defaultName;
    //private final AtomicLong counter;

    public CoffeeshopResource(DBI jdbi, String template, String defaultName) {
        this.itemDao = jdbi.onDemand(ItemDAO.class);
        this.template = template;
        this.defaultName = defaultName;
        //this.counter = new AtomicLong();
    }

    //Sample usage:
    //final int cost = 0;
    //final String value = String.format(template, defaultName);
    //return new Coffeeshop(((int) counter.incrementAndGet()), value, cost);

    //#index
    @GET
    @Timed
    @Path("/items.json")
    public List<Coffeeshop> findAllOrder() {
        // retrieve all order items
        List<Coffeeshop> itemList = itemDao.findAllItem();
        if (itemList != null) {
            return itemList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //new


    //#create
    @POST
    @Timed
    @Path("/items.json/{id}")
    public Response updateOrderItem(Coffeeshop item, @PathParam("id") int menuId)
        //implements Switch
    {
        //menuDao = getMenuDao();
        //CoffeeMenu menu = menuDao.findMenu(menuId);




        try {
            itemDao.updateItem(item.getName(), item.getCostInCents());
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
    public Coffeeshop findOrder(@PathParam("id") int id) {
        // retrieve information about the drink with the provided id
        Coffeeshop item = itemDao.findItemById(id);
        if (item != null) {
            return item;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //#destroy
    @DELETE
    @Timed
    @Path("/items.json/{id}")
    public Response cancelItem(@PathParam("id") int id){

        //TODO How to delete based on id only in a non-Coffeeshop JSON object ?
        System.err.println(id);
        try{
            itemDao.removeItem(id);
            return Response.noContent().build();
        }
        catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
            //return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    //Not supported yet=============================================================================

    /*
    @GET
    @Timed
    @Path("/items.json/{userPattern}")
    public List<Coffeeshop> findGroupedItem(@PathParam("userPattern") String userPattern) {
        System.err.println(userPattern);
        // retrieve grouped items
        List<Coffeeshop> itemList = itemDao.findItemByName("%" + userPattern + "%");
        if (itemList != null) {
            return itemList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @POST
    @Timed
    @Path("/cancel_group_item/{userPattern}")
    public Response cancelGroupItem(@PathParam("userPattern") String userPattern){

        //TODO How to delete based on userPattern in a non-Coffeeshop JSON object ?
        System.err.println(userPattern);
        try{
            itemDao.removeGroupItemByName("%" + userPattern + "%");
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
