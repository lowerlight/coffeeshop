package com.coffeeshop.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * *************************************************************************************************
 * <p/>
 * Project : coffeeshop-api
 * Filename : TemplateHealthCheck.java
 * <p/>
 * *************************************************************************************************
 */

public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String CoffeeshopMenu = String.format(template, "TEST");
        if (!CoffeeshopMenu.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }


}
