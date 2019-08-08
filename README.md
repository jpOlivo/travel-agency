# Getting Started

API Rest for retrieve recommendations of hotels and flight reservations. It provides a single endpoint that returns:

* Flight reservations list (ID and date) for an destination (ordered by date)
* Hotels list for a destination (name and address).

### Reference Documentation
For further reference, please consider the following sections:

#### Design

![img](https://i.imgur.com/Fh1Y6O0.png)

1. Periodically, **Recommendation API** get data from **Flight Reservation API** and store it. 

2. The data obtained from **Flight Reservation API** is persisted on a database. 

3. For each request to **Recommendation API**:

   3.1. The data from flight reservation is querying from database
	 
   3.2. The data from hotels are querying on cache. If are not present, are retrieved from **FourSquare API**. 
   
   3.3. The cache of hotels is updated 
   
   3.4. The information of flights and hotels is bundled in a DTO that is returned to caller
   

4. The cache(Caffeine) and database(H2) are in-memory implementations

The points 1. and 2. allow that the flight reservations are not lose

The cache mentioned on 3.2. and 3.3. allow improve the performance by reducing the data processing and fetching


#### API Rest

The API documentation can be found [here](http://localhost:8080/swagger-ui.html#)

The following examples illustrate how to use the defined endpoints:

1. Request on *recommendations* endpoint:

`$ curl -X GET "http://localhost:8080/v1/recommendations/Paris" -H "accept: */*"`

`$ curl -X GET "http://localhost:8080/v1/recommendations/Roma" -H "accept: */*"`

#### Tests

A set of unit and integration tests can be found under */src/test/java*

Use 'mvn test' for execute the unit tests  

`$ mvn test`

Use 'mvn verify' for execute the integration tests

`$ mvn verify`


#### Running the API

1. Clone this [repository](https://github.com/jpOlivo/travel-agency)

2. Execute the app through Spring Boot Maven plugin

`$ ./mvnw spring-boot:run`


#### Further consideration
Of course this example just shows the prototypical implementation. When adapting it for a real-life scenario I suggest the following optimizations:

* Use of messages brokers (ActiveMQ, RabbitMQ, Kafka, etc.) for avoid that the data provided by flight reservations API are lost. Also, this allow decouple the services. 
* Alternatively, is possible define a new **reactive endpoint** on Flight Reservation API , of manner that the clients can subscribes at it and are notified each time that a reservation is performed 
* Use of a logging system for recording of activity 
* Make wide use of javadoc and comments on the classes ;)
* Add alternatives paths on the tests for achieve major coverage
* Use of persistent caches and databases (Redis, etc.)
