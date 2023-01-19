# Microservice template
## Summary
Seed repo for all new services

## Technology stack
* Java 17
* Spring Boot 2.7.5
* Spring Cloud 2021.0.5
* Maven 3.6.3

## OpenAPI Specification
Please add OpenAPI specification if exists. Currently automatic generation.

## Database schema
Generated database schema can be found [here](./db-schema.svg).

## Configuration properties

The application uses Spring Boot as the main framework so all the properties from the [application.yml](./src/main/resources/application.yml) and other (profile-specific) configs could be overridden using the [relaxed binding feature](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.relaxed-binding).

Some properties also have aliases for the convenience (e.g. `${PORT}` for `server.port`).

## Database configuration

Microservice uses PostgreSQL as a main database.
You should provide an running instance before start of application and fill environment variables:

- `DB_URL` *jdbc:postgresql://localhost:5432/postgres*
- `DB_USERNAME` *postgres*
- `DB_PASSWORD` *postgres*

## Database migrations rollout
The application uses [Liquibase](https://www.liquibase.org/) for applying DB migrations which happens during the startup.

## Running locally
To run it locally:

Run docker container with postgres image (also specify credential to connect in configuration)

## How to run tests
This project has JUnit tests. To run them execute this command from maven plugin or terminal:
```text
mvn test
```
To run integration tests, you need to start docker locally.

## Maintainers
- [@Byndyusoft/owners](https://github.com/orgs/Byndyusoft/teams/owners) <<github.maintain@byndyusoft.com>>
- [@Byndyusoft/team](https://github.com/orgs/Byndyusoft/teams/team)
- [@Byndyusoft/java-admins](https://github.com/orgs/Byndyusoft/teams/java-admins)

## License
This repository is released under version 2.0 of the
[Apache License](https://www.apache.org/licenses/LICENSE-2.0).