# Photo App

A Spring application which follows micro-service architecture.
This project is created during a course for learning different micro-service concepts.

## Concepts learned

- Created a micro-service from scratch.
- Added jwt authentication with refresh token at service level and at API gateway level.
- Created discovery service which will register all the micro-services, so that our application will be aware of its network address and its port number.
- Created API gateway for load balancing, as a reverse proxy.
- Created Custom pre/post Gateway filters and added Authorization functionality to verify the jwt token at Gateway level.
- Created a centralized Config server so that all the global/specific application properties will be fetch from the GitHub [repository](https://github.com/CryptoSingh1337/photo-app-config-server).
- Created encryption and decryption for the config server using both shared encryption key and asymmetric key.

## Tech Stack

- Spring Boot
- Spring Cloud Netflix Eureka Server and Client
- Spring Cloud Gateway
- Spring Cloud Bus
- Spring WebMvc
- Spring Data Jpa
- H2-DB and MySQL
