# Shopping Cart Demo Web Application

## Introduction
The Web Application emphasises of working of simple shopping cart web applcaition. Which includes role based authentication and authorization, registering user
,adding inventory via excel file, introducing offer discounts for items and simple checkout.

## Prerequisites

1. Java11
2. Maven
3. IntelliJ
4. MySQL 8.0.25

## Installation

1. install Java11,Maven,MySQL
2. Take pull of repository master.
3. Open project via intellij by file -> open -> project -> pom.xml
4. Maven will do its dependency work.
5. Configure database credentials in application.properties file in resources folder of project.
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/super-market
spring.datasource.username=root
spring.datasource.password=
```

6. After resolving dependencies.
7. Now we will import dump of our database schema its in resources folder of project.
8. first create schema with name 'super-market', then import dump in it.
9. Now we are good to go.

## Project Architecture

Its Design on MVC architecture and also build rest apis for shopping cart. There are three layers
1. Controller
2. Service
3. Repository ( DAO )

## How it works 

1. admin is the default user of app with ADMIN roles so username:admin password:admin
2. After login admin is able to see no inventories because there none in DB.
3. On right upper corner you are able to see Import Inventories button. ( only Admin has right to see it ).
4. There is inventories.xlsx file in resources folder upload it.
5. Now you can see inventories.
6. Logout
7. Register as a user.
8. Login, now you cannot see import inventories button because user has no rights to see it.
9. User can add items to cart and can check his cart on upper right corner nav bar.
10. There is different offer on specific items that user wants to avail the discount.
11. Depending on item the cart will calculate discount and total.
12. User can also delete the items from the cart.
13. Validation for availability of item is also implemented.
14. Checkout cart.

## Problem Faced ( Important )

1. I didn't understand the cart coupon code strategy instead I implemented item specific offer logic hope this will cater your requirements.

## Technologies Used

1. Spring boot ( rest )
2. Spring boot ( mvc )
3. MySQL 8.0.25
4. Thymeleaf Template Engine.
5. Spring Security
6. Hibernate ORM
7. Apache POI
8. Bootstrap
9. JavaScript

## Video link

Project Demo; https://drive.google.com/drive/folders/1RWml-43RBisDkO7sXAHNSayQuL5kqktV?usp=sharing
