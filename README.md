# wishlist-kolnicka-2021

<p>Demo app for presentation about Spring Boot [@Kôlnička](https://www.howknow.sk/kolnicka/) 
It illustrates usage of basic Spring Boot features and tools:</p>

* **@RestController** - generate 1st REST webservice ([GreetingController](src/main/java/dk/cngroup/kolnicka/greeting/GreetingController.java))
* **@RestController** - generate our own REST webservices which connects to DB ([WishListController](src/main/java/dk/cngroup/kolnicka/wishlist/WishListController.java))
* **Project Lombok** - avoids boiler code e.g.generated getters, setters, constructors ([example](src/main/java/dk/cngroup/kolnicka/wishlist/Wish.java), [documentation](https://projectlombok.org/features/all))
* **Spring Data Repository** - generate basic CRUD methods to work with entity Whish ([WishRepository](src/main/java/dk/cngroup/kolnicka/wishlist/WishRepository.java))
* **Spring MockMvc** - allows writing integration test for REST services ([GreetingIntegrationTest](src/test/java/dk/cngroup/kolnicka/GreetingIntegrationTest.java), [WishListIntegrationTest](src/test/java/dk/cngroup/kolnicka/WishListIntegrationTest.java))
* **Spring Data REST** - automatic exposure of Spring Data repositories via REST API ([spring-boot-starter-data-rest dependency](build.gradle) )
* **Flyway** - tool to automate deployment of DB scripts / DB migration ([Flyway init script](src/main/resources/db/migration/V00001__Init.sql), [gradle dependency](build.gradle)))
* **H2 Console** - tool to see your DB content ([H2 Console](http://localhost:8080/h2))


Project uses **H2 database** stored in file ([config](src/main/resources/application.properties)). For tests DB is store just in memory. ([config](src/test/resources/application.properties))

Useful runtime URLs:
* **[Hello World](http://localhost:8080/hello)**
* **[Hello Spring](http://localhost:8080/hello/Spring)**
* **[List all wishes](http://localhost:8080/wishes)**
* **[H2 Console](http://localhost:8080/h2)**
