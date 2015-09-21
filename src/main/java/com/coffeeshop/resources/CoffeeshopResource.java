package com.coffeeshop.resources;

import com.codahale.metrics.annotation.Timed;
import com.coffeeshop.core.Coffeeshop;
import jersey.repackaged.com.google.common.base.Optional;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
public class CoffeeshopResource {

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

    //Ambiguous return type, so use pattern instead of id (unlikely need to use id)D
/*    @GET
    @Timed
    @Path("/{id}")
    public Coffeeshop findOrder(@PathParam("id") int id) {
        // retrieve information about the drink with the provided id
        Coffeeshop item = itemDao.findItemById(id);
        if (item != null) {
            return item;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }*/

    @GET
    @Timed
    @Path("/all")
    public List<Coffeeshop> findAllOrder() {
        // retrieve all order items
        List<Coffeeshop> itemList = itemDao.findAllItem();
        if (itemList != null) {
            return itemList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @GET
    @Timed
    @Path("/{userPattern}")
    public List<Coffeeshop> findGroupedItem(@PathParam("userPattern") String userPattern) {
        // retrieve all order items
        List<Coffeeshop> itemList = itemDao.findItemByName("%" + userPattern + "%");
        if (itemList != null) {
            return itemList;
        } throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
