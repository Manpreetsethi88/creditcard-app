# Credit Card Application

A spring boot application that exposes 2 RestEndpoints to execute credit cart related operations i.e Add new cards and list all cards from a system.

- /addCard = adds a new Credit Card
- /getAllCards = List all the credit cards from the system

# Technology Stack

- Spring Boot 2.5.9 Release
- HSQLDB Database Runtime (In-Memory)
- Spring Boot JDBC
- SpringFox Swagger
- SpringFox UI
- Junit
- Maven 4.0

# Development Environment Setup

- Import as maven project in Intellij or Eclipse.

### SpringFox Swagger
##### Swagger UI will be available on the following link:

- <http://localhost:8080/creditcardapp/swagger-ui/#/>
- <http://localhost:8080/creditcardapp/v2/api-docs>

For demo basic auth username: admin and password: password

### Maven Run
```mvn
mvn clean install
mvn spring-boot:run
```