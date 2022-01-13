# learn-spring-boot
Learning spring boot

## springbootproject
### What it does
- REST API for Customer Object
- REST API performs update to a MySQL database
- Test cases for REST API.

### Technologies used
- Hibernate, JPA and mysql driver for data mapping and saving data to database
- Mockito and Assert to test REST API

### Structure
- Rest controller: CustomerRestController
- Test file: TestingCustomerRestAPI
- REST structure:
  - GET: /api/customer. Get all customers
    - /api/customer/{id}: Get customer with id
  - POST: /api/customer. Create a customer
  - PUT: /api/customer. Update a customer
  - DELETE: /api/customer/{id}. Delete a customer
