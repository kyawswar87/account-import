# Getting Started
This application is sample demonstration Spring Boot project for csv file upload, parsing, inserting into database and retrieving. But I used a very common useful features in Spring Boot.
# Authentication
I used JWT token based. Token lifetime is 24 hours. There are two roles in this project: `ROLE_ADMIN` and `ROLE_USER`.

# Role Admin
Admin role can upload csv file and update the description.

# Role User
User only can retrieve the data.

# Concurrent Update
I used @version for concurrent update protection.

# lombok
I used lombok for repetitive getter, setter, equal, hashcode and other methods such as Builder method pattern.

# liquibase
I used liquibase for database versioning.

# OpenAPI Document generator
I also used openAPI for API document generator. `http://localhost:8080/swagger-ui/index.html`

# Mock
I also used mocking in Junit for testing purpose.

# Pagination
I added pagination in retrieve endpoint.

# ThreadPoolTaskExecutor
This is for Async Task which is cvs file parsing and inserting into database.

# Exception Handling
I used `@ControllerAdvice` for global exception handling.
