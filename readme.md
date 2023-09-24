  ### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

## Spring Boot Employee Management System

This Spring Boot project is a simple Employee Management System that allows you to perform CRUD operations on employee records. Below are the changes made to the `EmployeeController` and an overview of the modifications.

## Changes Made

### Improved Endpoint URLs

1. **Original URL**: `/api/v1`  
   **Modified URL**: `/api/v1/employees`

   We improved the organization of endpoints by moving them under the `/employees` path. This makes the API more intuitive and organized.

### Response Entities

2. **Original**: Directly returning domain objects or using `void`  
   **Modified**: Returning `ResponseEntity` for better control over HTTP responses

   We enhanced the responses by returning `ResponseEntity` objects. This provides a clear way to handle various HTTP status codes and response bodies.

### HTTP Status Codes

3. **Original**: Not consistently returning appropriate HTTP status codes  
   **Modified**: Using standard HTTP status codes for responses

   We revised the controller methods to return standard HTTP status codes, such as `200 OK`, `201 Created`, `204 No Content`, and `404 Not Found`, to improve the API's consistency and clarity.

### Validations

4. **Original**: Minimal input validation  
   **Modified**: Added input validation checks for `POST` and `PUT` requests

   We added input validation checks to ensure that invalid data is rejected with a `400 Bad Request` status.

### Request Body

5. **Original**: Accepting `Employee` objects as method parameters  
   **Modified**: Accepting `Employee` objects as request bodies

   We modified the `@RequestBody` annotation to indicate that the `Employee` object is part of the request body, improving clarity.

### Role-Based Access Control

This Spring Boot project supports role-based access control (RBAC) to restrict access to specific API endpoints based on user roles. Here are the details of role-based access control in this project:

#### Protecting `deleteEmployee` and `updateEmployee` Endpoints

The `deleteEmployee` and `updateEmployee` endpoints have been secured using the `@Secured` annotation to ensure that only users with the `ROLE_ADMIN` role can access them. Here's how it works:

- `deleteEmployee`: This endpoint allows the deletion of employee records. To access this endpoint, a user must have the `ROLE_ADMIN` role. Users without this role will receive a 403 Forbidden response if they attempt to delete an employee.

- `updateEmployee`: This endpoint allows the updating of employee records. Similar to `deleteEmployee`, it requires the `ROLE_ADMIN` role for access. Unauthorized users will receive a 403 Forbidden response when attempting to update an employee.

#### How to Test Role-Based Access

To test role-based access control, you can follow these steps:

1. Try to access these endpoints with user accounts that do not have the `ROLE_ADMIN` role. You should receive a 403 Forbidden response, indicating that access is restricted.

By implementing role-based access control in this manner, you can ensure that sensitive operations like deleting and updating employee records are protected and only accessible to authorized users with the `ROLE_ADMIN` role.


### Added relevant dependencies

- Spring security and mockito for the security and test. 


### Future Improvements and Plan

In the future, we can plan to further enhance this Employee Management System by implementing the following features and improvements:

1. **User Authentication**: Implement user authentication and role-based access control to secure the API endpoints. This will allow different levels of access for administrators and regular users.

2. **Pagination**: Introduce pagination support for listing employees. This will improve the efficiency of handling large datasets.

3. **Validation Enhancements**: Strengthen input validation and data validation logic to ensure data integrity and security.

4. **Logging**: Implement comprehensive logging to track API requests and responses for monitoring and debugging purposes.

5. **Database Integration**: Explore integration with a production-ready database such as MySQL or PostgreSQL for data persistence.

6. **Exception Handling**: Enhance exception handling to provide meaningful error messages and responses to clients.

7. **Testing**: Expand the test coverage by adding unit tests, integration tests, and end-to-end tests to ensure the reliability of the application.

8. **Documentation**: Continue to improve code comments and generate API documentation using tools like Swagger for better developer and user experience.

9. **Frontend**: Develop a user-friendly frontend interface for managing employees, making it accessible through a web-based dashboard.


#### My experience in Java

- I have 3 years experience in Java and I started to use Spring Boot from last 2 year.
