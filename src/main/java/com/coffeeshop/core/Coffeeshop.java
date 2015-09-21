package com.coffeeshop.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : Coffeeshop.java
 * <p/>
 * *************************************************************************************************
 */

public class Coffeeshop {
    private int id;
    private String name;
    private int costInCents;

    public Coffeeshop() {
        // Jackson deserialization
    }

    public Coffeeshop(int id, String name, int costInCents) {
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
