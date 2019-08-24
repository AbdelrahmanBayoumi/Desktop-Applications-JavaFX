package project;

import hotel.Guest;
import hotel.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBase {

    public static void CheckConnection() throws SQLException {
        String dataBaseName = "hotel";
        System.out.println("Test Connection DataBase :" + dataBaseName);
        String url = "jdbc:mysql://localhost:3306/" + dataBaseName;
        Connection con = DriverManager.getConnection(url, "root", "");
        System.out.println("connection success");
        con.close();
    }

    //=============================================
    //  Rooms 
    public void Save10Room() {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        // create a student object
        System.out.println("Creating new room object...");

        // start a transaction
        session.beginTransaction();

        try {
            for (int i = 0; i < 10; i++) {

                // save the student object
                System.out.println("Saving the room...");
                Room r = new Room("Vip", "Triple", date, date, true);
                session.save(r);

                // commit transaction
                System.out.println("Done!");

            }
        } catch (Exception e) {
            System.out.println("SaveRoom Error");

        } finally {
            session.getTransaction().commit();
            factory.close();
        }
    }

    public void SaveRoom(Room room) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a student object
            System.out.println("Creating new room object...");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the room...");
            session.save(room);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("SaveRoom Error");
        } finally {
            factory.close();
        }
    }

    public void ReadRoom(Room room) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + room.getRoomID());

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + room.getRoomID());

            Room r = (Room) session.get(Room.class, room.getRoomID());

            System.out.println("Get complete: " + r);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    public static List<Room> getrooms() {
        List<Room> rooms = null;
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();
            // query students
            rooms = session.createQuery("from Room").list();
            // display the students
            displayList(rooms);
            session.getTransaction().commit();

            System.out.println("getRooms() Done!");
        } finally {
            factory.close();
        }

        return rooms;
    }

    public static List<Room> getAvailableRooms() {
        List<Room> rooms = null;
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();
            // query students
            rooms = session.createQuery("from Room r where r.isEmpty=true").list();
            // display the students
            displayList(rooms);
            session.getTransaction().commit();

            System.out.println("getRooms() Done!");
        } finally {
            factory.close();
        }

        return rooms;
    }

    //======================================
    // Users 
    public static boolean SaveUser(User user) {
        List<User> users = DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                return false;
            }
        }

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a User object
            System.out.println("Creating new User object...");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the User...");
            session.save(user);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("SaveUser Error");
        } finally {
            factory.close();
        }
        return true;
    }

    public static boolean isUserNameValid(String username) {
        List<User> users = DataBase.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                System.out.println("Equal");
                return true;
            }
        }
        System.out.println("Not Equal");
        return false;
    }

    public static boolean DeleteUser(String username) {
        System.out.println(" deleteUser() ... ");
        if (!isUserNameValid(username)) {
            System.out.println("not valid username");
            return false;
        }
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Deleting User username = '" + username + "'");
            int executeUpdate = session.createQuery("delete from User where user_name = '" + username + "'").executeUpdate();
            // commit the transaction
            session.getTransaction().commit();
            System.out.println("deleteGuest() Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
        return true;
    }

    public static List<User> getUsers() {
        List<User> users = null;
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query users
            users = session.createQuery("from User").list();

            // display the users
            displayList(users);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("getUsers() Done!");
        } finally {
            factory.close();
        }
        return users;
    }

    //=====================================
    // guests
    public static List<Guest> getGuests() {
        List<Guest> guests = null;
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Guest.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query Guest
            guests = session.createQuery("from Guest").list();

            // display the Guest
            displayList(guests);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("getGuest() Done!");
        } finally {
            factory.close();
        }
        return guests;
    }

    //======================================
    // print Data 
    public static <T> void displayList(List<T> list) {
        for (T tempUser : list) {
            System.out.println(tempUser);
        }
    }

}
