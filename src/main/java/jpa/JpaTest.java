package jpa;

import fr.istic.taa.jaxrs.dao.*;
import fr.istic.taa.jaxrs.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JpaTest {

    TicketDao ticketDAO = new TicketDao();
    TagDao tagDAO = new TagDao();
    CommentDao commentDAO = new CommentDao();
    UserDao userDAO = new UserDao();

    public static void main(String[] args) {
        JpaTest test = new JpaTest();
        test.createTickets();
        List<Ticket> resultList = test.ticketDAO.findAll();
        System.out.println("Number of tickets : " + resultList.size());
        for (Ticket next : resultList) {
            System.out.println("Ticket : " + next);
        }
    }

    private void createTickets() {

        // create users
        User user0 = new Admin("Adrien", "adrien@admin.fr");
        User user1 = new User("Alice", "alice@user.fr");
        User user2 = new User("Bob", "bob@user.fr");
        User user3 = new User("Charlie", "charlie@user.fr");

        // create tags
        Tag tag1 = new Tag("bug");
        Tag tag2 = new Tag("feature");
        Tag tag3 = new Tag("performance");
        Tag tag5 = new Tag("security");
        Tag tag6 = new Tag("authentication");
        Tag tag4 = new Tag("design");
        Tag tag7 = new Tag("usability");

        // create tickets
        Ticket ticket1 = new Ticket("Bug in login page", "There is an error when trying to log in", user1, user2, new ArrayList<>(Arrays.asList(tag1)));
        Ticket ticket2 = new Ticket("New feature request", "Add support for dark mode", user2, user1, new ArrayList<>(Arrays.asList(tag2)));
        Ticket ticket3 = new Ticket("Issue with payment processing", "Transactions are not being processed correctly", user1, user2, new ArrayList<>(Arrays.asList(tag1, tag2)));
        Ticket ticket4 = new Ticket("Slow loading times", "The website takes too long to load", user2, user3, new ArrayList<>(Arrays.asList(tag3)));
        Ticket ticket5 = new Ticket("UI inconsistency", "The buttons on the page are not aligned properly", user1, user2, new ArrayList<>(Arrays.asList(tag4)));
        Ticket ticket6 = new Ticket("Password reset functionality", null, user2, user1, new ArrayList<>(Arrays.asList(tag5, tag6)));
        Ticket ticket7 = new Ticket("Improvements to search functionality", "The search results are not very relevant", user1, user2, new ArrayList<>(Arrays.asList(tag7)));

        // add comments to tickets
        Comment comment1 = new Comment("I am also experiencing this issue", user1, ticket1);
        ticket1.getComments().add(comment1);

        Comment comment2 = new Comment("We will consider adding this feature in the next release", user2, ticket2);
        ticket2.getComments().add(comment2);

        Comment comment3 = new Comment("Can you provide more information about the transactions?", user2, ticket3);
        ticket3.getComments().add(comment3);

        Comment comment4 = new Comment("I have also noticed this issue", user1, ticket4);
        ticket4.getComments().add(comment4);

        Comment comment5 = new Comment("We are investigating the cause of this issue", user0, ticket4);
        ticket4.getComments().add(comment5);

        Comment comment6 = new Comment("I have noticed this too", user2, ticket5);
        ticket5.getComments().add(comment6);

        Comment comment7 = new Comment("I think this is an important issue that needs to be addressed soon", user1, ticket6);
        ticket6.getComments().add(comment7);

        // persist users
        userDAO.save(user0);
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);

        // persist tags
        tagDAO.save(tag1);
        tagDAO.save(tag2);
        tagDAO.save(tag3);
        tagDAO.save(tag4);
        tagDAO.save(tag5);
        tagDAO.save(tag6);
        tagDAO.save(tag7);


        // persist tickets
        ticketDAO.save(ticket1);
        ticketDAO.save(ticket2);
        ticketDAO.save(ticket3);
        ticketDAO.save(ticket4);
        ticketDAO.save(ticket5);
        ticketDAO.save(ticket6);
        ticketDAO.save(ticket7);


        // persist comments
        commentDAO.save(comment1);
        commentDAO.save(comment2);
        commentDAO.save(comment3);
        commentDAO.save(comment4);
        commentDAO.save(comment5);
        commentDAO.save(comment6);
        commentDAO.save(comment7);

    }

}
