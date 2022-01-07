## Access data with JPA
https://spring.io/guides/gs/accessing-data-jpa/
- Need to define Enity class for object mapping
- As well as ID auto generate 
- Thymeleaf:
  - Relies on data binding, in case it needs to set data, that object need to have public get and set method, what about public attribute?
- Repository can custom method
  - [common action][capitalize attribute] matches with [no capitalize first letter attribute]
  - findByLastName will look for attribute lastName
