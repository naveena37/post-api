## Post API ##
The Post API is an Aggregator API to retrieve posts and comments for a given user.

## Prerequisites
Java version:   1.8
Gradle version: 4.8

[Note:To upgrade to Java 11, several DropWizard libraries and Gradle distribution has to undergo upgrades]

## Build shadowJar
```bash
./gradlew shadowJar
```
Java and gradle versions are displayed in console as
```bash
Configure project :
Running java version: 1.8.0_191
Running gradle version: 4.8
```

## Host the API
```bash
java -jar build/libs/post-api-1.0-SNAPSHOT-all.jar server src/yml/server.yml
```
Can see the following in console..

```bash
2020-12-10 18:18:53,731 3602 INFO [main] [] i.d.j.DropwizardResourceConfig - The following paths were found for the configured resources:
    GET     /api/swagger (io.federecio.dropwizard.swagger.SwaggerResource)
    GET     /api/swagger.{type:json|yaml} (io.swagger.jaxrs.listing.ApiListingResource)
    GET     /api/v1/posts (nz.co.post.v1.api.resource.PostResource)
    GET     /api/v1/users (nz.co.post.v1.api.resource.PostResource)

2020-12-10 18:18:53,756 3627 INFO [main] [] o.e.j.s.Server - Started @3766ms
2020-12-10 18:18:53,756 3627 INFO [main] [] n.c.p.PostApplication - Application is up and running.
```

## Access the API using bundled Swagger UI
* Swagger UI location: 
    * http://localhost:8080/api/swagger
    * http://localhost:8080/api/swagger#/Users
    * http://localhost:8080/api/swagger#/Posts

### Unit Test
```bash
./gradlew test
```

### Test Results
Test results can be found in `[project directory]/build/reports/tests`

To see all available tasks `./gradlew task`

