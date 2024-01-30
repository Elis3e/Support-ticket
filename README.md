## Ticket Manager

This project is a ticket manager, similar to GitHub tickets, designed in technologies covered in class. It is a software that allows managing requests and issues of users in a system or application.

The application will enable users to create tickets to report problems, errors, or improvements. The tickets will be sorted and processed by system administrators who can filter, sort, and assign them to technical support team members.

The project will be developed using technologies learned in class such as Hibernate, Jpa, JaxRS, OpenAPI, Swagger.

## Class Model

![tickets_manager-class_model.png](src%2Fmain%2Fresources%2Ftickets_manager-class_model.png)


The **Ticket** class represents a ticket that can be submitted by a user through a web interface. The ticket contains information such as the title, description, status, creation date, update date, assigned user, creator user, and associated comments.

The **User** class represents a user of the application. The user can submit tickets, be assigned to tickets, and add comments. Users have a name and an email.

The **Comment** class represents a comment associated with a ticket. The comment contains text, a date, and the user who added the comment.

The **Tag** class represents a category or tag that can be assigned to a ticket. Tags are used to facilitate the classification and search of tickets.


## Setup
1. Install and start XAMPP or any other local database manager.
2. Start the MySQL service in the Control Panel.
3. Open phpMyAdmin in your web browser and create a new database named "ticket".
4. Start the backend by running the main class (RestServer) in your IDE.
5. Test the project with Swagger by accessing http://localhost:8080/api/ in your web browser. This will open the Swagger UI interface, where you can test the API endpoints of the application.
6. Test the JPA component of the project by running the main class (JpaTest) in your IDE. This will fill the database with some examples and allow you to test the functionality of the JPA component.

_Note that if you are want to use a different database than MySQL, you will need to update the persistence.xml file and the persistence unit name in the class EntityManagerHelper to reflect the database configuration.
These steps are general guidelines and may vary depending on the specific configuration of your local environment._
