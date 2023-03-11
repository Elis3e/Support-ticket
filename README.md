## Ticket Manager

This project is a ticket manager (backend part), similar to GitHub tickets, designed in one of the technologies covered in class. It is a computer software that allows managing requests and issues of users in a system or application.

The ticket manager will enable users to create tickets to report problems, errors, or improvements to an application or system. The tickets will be sorted and processed by system administrators who can filter, sort, and assign them to team members for resolution.

The project will be developed using technologies learned in class such as Hibernate, Jpa, JaxRS, OpenAPI, Swagger.


## Class Model

![Tickets manager.png](src%2Fmain%2Fresources%2FTickets%20manager.png)

### Explanation of the class model

The Ticket class represents a ticket that can be submitted by a user through a web interface. The ticket contains information such as the title, description, status, creation date, update date, assigned user, creator user, and associated comments.

The User class represents a user of the application. The user can submit tickets, be assigned to tickets, and add comments. Users have a name and an email.

The Comment class represents a comment associated with a ticket. The comment contains text, a date, and the user who added the comment.

The Tag class represents a category or tag that can be assigned to a ticket. Tags are used to facilitate the classification and search of tickets.


## Setup
1. Import this project in your IDE, 
2. Start the database*
3. Start the database viewer 
4. Start the backend. There is a main class (RestServer) to start the backend 

The project can be tested with Swagger at the address http://localhost:8080/api/. This will provide an easy-to-use interface for testing and interacting with the API endpoints of the application.

The JPA part of the project can be tested with the main class (JpaTest). This class fills the database with some examples, allowing you to test the functionality of the JPA component of the application.

*The project includes a configuration for MySQL, but you can use a different database by updating the persistence.xml file and the persistence unit name in the class EntityManangerHelper
