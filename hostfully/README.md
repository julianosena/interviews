# BOOKING HOSTIFULLY

## Block Management API

This API provides endpoints to perform CRUD operations on blocks.

### Endpoints

| Method | Endpoint                   | Description                 |
|--------|----------------------------|-----------------------------|
| GET    | `/blocks`                  | Retrieve all blocks         |
| GET    | `/blocks/{blockId}`        | Retrieve a specific block   |
| POST   | `/blocks`                  | Create a new block          |
| PUT    | `/blocks/{blockId}`        | Update a specific block     |
| DELETE | `/blocks/{blockId}`        | Delete a specific block     |

### Booking Management API

This API provides endpoints to manage bookings.

#### Endpoints

| Method | Endpoint                            | Description                                           |
|--------|-------------------------------------|-------------------------------------------------------|
| POST   | `/bookings`                         | Create a new booking                                  |
| PUT    | `/bookings/{bookingId}`             | Update booking dates and guest details                |
| POST   | `/bookings/{bookingId}/cancelation` | Cancel a booking                                      |
| POST   | `/re-bookings/{bookingId}`          | Rebook a canceled booking                       |
| DELETE | `/bookings/{bookingId}`             | Delete a booking from the system                      |
| GET    | `/bookings/{bookingId}`             | Get details of a specific booking                     |
| GET    | `/bookings`                         | Retrieve all bookings                                 |

### API Documentation

For more detailed information on request and response formats, start the application and access the swagger, click [here](https://localhost:8080/)

### Technical

This project demonstrates the implementation of Clean Architecture, a software design philosophy emphasizing maintainability and separation of concerns. Clean Architecture divides a system into layers, with clear boundaries, to enhance flexibility and testability.

![img_1.png](documentation/img/clea-architecture.png)

### Overview
**Entities**:<br /><br />
Represent core business entities with embedded business rules and logic.

**Use Cases (Interactors)**:<br /><br />
Contain application-specific business rules and orchestrate data flow between entities and interfaces.

**Interfaces (Adapters)**:<br /><br />
Connect use cases to external systems (e.g., databases, frameworks) and handle data conversion.

**Frameworks & Drivers**:<br /><br />
Utilize external frameworks and tools such as web frameworks, databases, and UI components, maintaining flexibility and ease of replacement.

**Project Structure**<br /><br />
The codebase is organized using Clean Architecture principles with distinct packages for domain entities, use cases, infrastructure (persistence and presentation), and the application entry point.

**Explanation**<br /><br />
All of classes should have one method and specific responsibility. The name of the operation / action / responsibility goes to the name of the class, and the class should have just one method named execute.
The code speaks for itself, no need comments or any explanation, for example: <br>

```java
CreateBookingUseCase createBookingUseCase = new CreateBookingUseCase();
Booking booking = new Booking();
createBookingUseCase.execute(booking);
```
You can see in the example above, that piece of code is going to create a booking, because It is explicit.</br>
It is just an example the system is different.

If You would like to know more about Clean Architecture, [click here](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

**Technologies**

* Java 17 as main programming language
* For tests, Groovy (Spock Framework). [Click here](https://spockframework.org/spock/docs/2.3/index.html)
* Framework Spring (Spring Boot)
* Database H2 in memory
* Liquibase for changelogs 
* Maven dependency managment