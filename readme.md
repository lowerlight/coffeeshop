== README

This README would normally document whatever steps are necessary to get the
application up and running.

This is the back-end of the web application Coffeeshop. It allows only one user
to:

1. Order 1 or more drinks
2. View his orders (total, grouped by tea/coffee, and grouped by size)

* Java version
This software was built using:
JDK             1.7.0_75
Apache Maven    3.0.5
Dropwizard      0.8.2

Versions of various Maven dependencies can be found in the pom.xml file.

* System dependencies
This back-end communicates with the front-end hosted on
through RESTful API



* Configuration and Building this application from source on Mac OS X:

The minimum version of the required Java JDK is 1.6
Apache Maven 3 is recommended, other versions are untested.

1. Install maven (see https://maven.apache.org/install.html)
2. Run
    $ mvn package

   Maven will read pom.xml to install all the dependencies required to
   produce the fat JAR containing the Dropwizard framework and compile
   the JAR required.

Note: An IntelliJ project file (.iml) has been provided too for convenience.

* Database creation
Done using sqlite3

* Database initialization
Done

* How to run the test suite
For now, done from the front end only.

* Services (job queues, cache servers, search engines, etc.)
RESTful API:

    DELETE  /coffeeshop/items/{id}.json (com.coffeeshop.resources.CoffeeshopResource)
    GET     /coffeeshop/items.json (com.coffeeshop.resources.CoffeeshopResource)
    GET     /coffeeshop/items/{id}.json (com.coffeeshop.resources.CoffeeshopResource)
    GET     /coffeeshop/menus.json (com.coffeeshop.resources.CoffeeshopResource)
    GET     /coffeeshop/menus/{id}.json (com.coffeeshop.resources.CoffeeshopResource)
    POST    /coffeeshop/items/new/{menuId}.json (com.coffeeshop.resources.CoffeeshopResource)

For admin only:
    POST    /tasks/log-level (io.dropwizard.servlets.tasks.LogConfigurationTask)
    POST    /tasks/gc (io.dropwizard.servlets.tasks.GarbageCollectionTask)

* Deployment instructions

To run this server locally:

Run
    $ java /path/to/coffeeshop-api-0.0.1-SNAPSHOT.jar server config.yaml



This is a Markdown file.
