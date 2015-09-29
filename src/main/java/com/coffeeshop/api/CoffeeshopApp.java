package com.coffeeshop.api;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.coffeeshop.resources.CoffeeshopResource;
import com.coffeeshop.health.TemplateHealthCheck;
import org.skife.jdbi.v2.DBI;

/**
 * *************************************************************************************************
 * <p/>
 * Project : Coffeeshop
 * Filename : CoffeeshopApp.java
 * <p/>
 * *************************************************************************************************
 */

public class CoffeeshopApp extends Application<CoffeeshopConf> {
    public static void main(String[] args) throws Exception {
        new CoffeeshopApp().run(args);
    }

    @Override
    public String getName() {
        return "coffeeshop";
    }

    @Override
    public void initialize(Bootstrap<CoffeeshopConf> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CoffeeshopConf config,
                    Environment env) {

        final DBI jdbi = new DBIFactory().build(env, config.getDataSourceFactory(), "sqlite3");

        final CoffeeshopResource resource = new CoffeeshopResource(jdbi, config.getTemplate(),
                config.getDefaultName());
        env.jersey().register(resource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(config.getTemplate());
        env.healthChecks().register("template", healthCheck);
    }
}
