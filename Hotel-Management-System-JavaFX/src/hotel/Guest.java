package hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "guest")
public class Guest implements RoomFees {

    @Column(name = "room_ID")
    private int roomID;
    @Column(name = "number_Of_Days")
    private int numberOfDaysStayed;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Address")
    private String Address;
    @Column(name = "city")
    private String city;
    @Column(name = "Nationality")
    private String Nationality;
    @Id
    @Column(name = "passport_Number")
    private String passportNumber;
    @Column(name = "phoneNo")
    private String phoneNo;
    @Column(name = "Card_Number")
    private String CardNumber;
    @Column(name = "card_Pass")
    private String cardPass;
    @Column(name = "fees")
    private double Fees;

    public Guest() {
    }

    public Guest(int roomID, int numberOfDaysStayed, String Name, String Email, String Address, String city, String Nationality, String passportNumber, String phoneNo, String CardNumber, String cardPass, double Fees) {
        this.roomID = roomID;
        this.numberOfDaysStayed = numberOfDaysStayed;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.city = city;
        this.Nationality = Nationality;
        this.passportNumber = passportNumber;
        this.phoneNo = phoneNo;
        this.CardNumber = CardNumber;
        this.cardPass = cardPass;
        this.Fees = Fees;
    }

    public double getFees() {
        return Fees;
    }

    public void setFees(double Fees) {
        this.Fees = Fees;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public String getCardPass() {
        return cardPass;
    }

    public void setCardPass(String cardPass) {
        this.cardPass = cardPass;
    }

    public int getNumberOfDaysStayed() {
        return numberOfDaysStayed;
    }

    public void setNumberOfDaysStayed(int numberOfDaysStayed) {
        this.numberOfDaysStayed = numberOfDaysStayed;
    }

    @Override
    public String toString() {
        return "Guest{" + "roomID=" + roomID + ", numberOfDaysStayed=" + numberOfDaysStayed + ", Name=" + Name + ", Email=" + Email + ", Address=" + Address + ", city=" + city + ", Nationality=" + Nationality + ", passportNumber=" + passportNumber + ", phoneNo=" + phoneNo + ", CardNumber=" + CardNumber + ", cardPass=" + cardPass + '}';
    }

    @Override
    public double CustomerRoomFees(Room room) {
        System.out.println("Inside CustomerRoomFees() ... ");
        double fees = 0;
        if (numberOfDaysStayed == 0) {
            fees += room.nightCost(room);
        }
        for (int i = 0; i < numberOfDaysStayed; i++) {
            fees += room.nightCost(room);
        }
        return fees;
    }

    /*==========================================================================
     ------------------------((((DataBase)))))----------------------------------
    ==========================================================================*/
    public static void SaveGuest(Guest guest) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Guest.class).buildSessionFactory();
        System.out.println("create session factory");

        // create session
        Session session = factory.getCurrentSession();
        System.out.println("create session");
        try {

            // start a transaction
            session.beginTransaction();
            System.out.println("start a transaction");
            // save the guest object
            System.out.println("Saving the guest...");
            session.save(guest);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("SaveGuest Error");
        } finally {
            factory.close();
        }
    }

    public static void deleteGuest(int roomID) {

        System.out.println(" deleteGuest() ... ");
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Guest.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Deleting guest roomID = " + roomID);
            session.createQuery("delete from Guest where roomID=" + roomID).executeUpdate();

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("deleteGuest() Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }

    }

}
