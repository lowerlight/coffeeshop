package com.coffeeshop.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : Menu.java
 * <p/>
 * *************************************************************************************************
 */

public class Menu {
    private int id;
    private String name;
    private int costInCents;

    public Menu() {
        // Jackson deserialization
    }

    public Menu(int id, String name, int costInCents) {
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
