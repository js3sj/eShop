# E-SHOP project

## About
Project simulates e-shop with functionalities:

- Create, view new customers/users.
- Create, view, update, delete items.
- Add items to the basket.
- View added items in the basket.
- Place, delete, view orders.

## Technologies
- Java
- SpringBoot
- SpringSecurity
- SpringThymeleaf
- JPA
- MySQL
- HTML
- CSS
- BootStrap

## Instructions how to use the project
The project is written in JAVA programming language with Eclipse IDE.

- First you need to import the project into your IDE by downloading or cloning it from GitHub.

- Check the application.properties file for more details regarding SpringBoot and MySQL database configuration: shema, url, port, username, password and etc.:

spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:mysql://localhost:3306/eshop

spring.datasource.username=yourUserName

spring.datasource.password=yourPassword

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql: true

- Create a database in MySQL workbench. 

- To start the application, run it in your IDE as Spring Boot App and 

- Execute the following SQL INSERT statements to create two new users: 

First user `admin` with password `admin` and role `ADMIN`

Second user `user` with password `user` and role `USER`

```
INSERT INTO `users` (`username`,`password`,`enabled`, `name`, `surname`, `address`)

VALUES ('admin','$2a$12$8um7u23O.G0qOxGdWciMF.8W7vjJKeGVJAfHUT5xV9kEtV13U07Ze',1,'name','surname','address');

INSERT INTO `users` (`username`,`password`,`enabled`, `name`, `surname`, `address`)

VALUES ('user','$2a$12$4HJ/8/rd7woAb0gAB8FbxOr8i8T8eNq/1ynjPJtTMcQhC36NQ2gfS',1,'name','surname','address');

INSERT INTO `roles` (`role_id`,`name`) VALUES (1, 'USER');

INSERT INTO `roles` (`role_id`,`name`) VALUES (2, 'ADMIN');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 2); -- user admin has role ADMIN 

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 1); -- user user has role USER
```

type in your browsers search field: localhost:8080/

## Basic structure of the project
### Entities: 
#Item with its own Repository
- id
- name
- description
- price
- photos

#Order with its own Repository
- id
- CustomerId/user_id
- TotalAmount

#Basket with its own Repository
- order_id
- items_id

#User with its own Repository
- id
- username
- password
- enabled
- name
- surname
- address

#UsersRoles with its own Repository
- user_id
- role_id

#Role with its own Repository
- role_id
- name

#SessionUser with its own Repository
- id
- user_id

### DTO's:
- itemDTO
- userDTO
- orderDTO

### AUTHORITIES FOR USERS:
- 1: `USER`
- 2: `ADMIN`

### CONTROLLERS: 
#ItemController /Authority
- Create /`ADMIN`
- View /`ADMIN`/`USER`
- Edit /`ADMIN`
- Delete /`ADMIN`

#OrderController /Authority
- Create /`ADMIN`/`USER`
- View /`ADMIN`/`USER`
- View all /`ADMIN`
- Edit /`ADMIN`
- Delete /`ADMIN`

#BasketController

#LoggingController

#UserController

#MainController