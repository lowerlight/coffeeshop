package com.coffeeshop.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : CoffeeMenu.java
 * <p/>
 * *************************************************************************************************
 */

public class CoffeeMenu {
    private int id;
    private String name;
    private int costInCents;

    public CoffeeMenu() {
        // Jackson deserialization
    }

    public CoffeeMenu(int id, String name, int costInCents) {
        this.id = id;
        this.name = name;
        this.costInCents = costInCents;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public int getCostInCents() {
        return costInCents;
    }
}
