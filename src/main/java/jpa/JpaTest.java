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
        User user1 = new User("Alice", "alice@example.com");
        User user2 = new User("Bob", "bob@example.com");

        // create tags
        Tag tag1 = new Tag("bug");
        Tag tag2 = new Tag("feature");

        // create tickets
        Ticket ticket1 = new Ticket("Bug in login page", "There is an error when trying to log in", user1, user2, new ArrayList<>(Arrays.asList(tag1)));
        Ticket ticket2 = new Ticket("New feature request", "Add support for dark mode", user2, null, new ArrayList<>(Arrays.asList(tag2)));
        Ticket ticket3 = new Ticket("Issue with payment processing", "Transactions are not being processed correctly", user1, user2, new ArrayList<>(Arrays.asList(tag1, tag2)));

        // add comments to tickets
        Comment comment1 = new Comment("I am also experiencing this issue", user1, ticket1);
        ticket1.getComments().add(comment1);

        Comment comment2 = new Comment("We will consider adding this feature in the next release", user2, ticket2);
        ticket2.getComments().add(comment2);

        Comment comment3 = new Comment("Can you provide more information about the transactions?", user2, ticket3);
        ticket3.getComments().add(comment3);

        // persist users
        userDAO.save(user1);
        userDAO.save(user2);

        // persist tags
        tagDAO.save(tag1);
        tagDAO.save(tag2);

        // persist tickets
        ticketDAO.save(ticket1);
        ticketDAO.save(ticket2);
        ticketDAO.save(ticket3);

        // persist comments
        commentDAO.save(comment1);
        commentDAO.save(comment2);
        commentDAO.save(comment3);

    }

}
