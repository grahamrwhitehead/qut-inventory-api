# QUT Inventory Item API

Spring Boot Server implementation generated from the supplied `inventory-api-spec.yml` file.

## Overview
This server has been generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project.

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)

You can view the api documentation in swagger-ui by pointing to http://localhost:8080/

This application has been secured using Basic Authentication. THe following accounts will allow access:

| Username | Password  |
| ---------| --------- |
| user1    | password  |
| user2    | password  |
| user3    | password  |

Refer to `InventoryApiControllerTest` for a suite of tests exercising each endpoint.

Sample data is loaded into an in-memory database on startup.

The H2 console may be accessed at: http://localhost:8080/h2-console

Username is `sa` and there is no password.

The following query will show the table contents:
```
SELECT *
FROM INVENTORY_ITEM i
         INNER JOIN MANUFACTURER m ON i.manufacturer_id = m.id;
```
