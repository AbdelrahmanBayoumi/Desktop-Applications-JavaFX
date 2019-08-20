package hotel;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import project.DataBase;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomID")
    private int roomID;
    @Column(name = "room_Type")
    private String room_Type;
    @Column(name = "room_capacity")
        private String room_capacity;
    @Column(name = "Check_In_Date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Check_In_Date;
    @Column(name = "Check_Out_Date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Check_Out_Date;
    @Column(name = "isEmpty")
    private boolean isEmpty;

    public Room(String room_Type, String room_capacity, Date Check_In_Date, Date Check_Out_Date, boolean isEmpty) {
        this.room_Type = room_Type;
        this.room_capacity = room_capacity;
        this.Check_In_Date = Check_In_Date;
        this.Check_Out_Date = Check_Out_Date;
        this.isEmpty = isEmpty;
    }

    public Room() {
    }

    public boolean isIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoom_Type() {
        return room_Type;
    }

    public void setRoom_Type(String room_Type) {
        this.room_Type = room_Type;
    }

    public String getRoom_capacity() {
        return room_capacity;
    }

    public Date getCheck_In_Date() {
        return Check_In_Date;
    }

    public void setCheck_In_Date(Date Check_In_Date) {
        this.Check_In_Date = Check_In_Date;
    }

    public Date getCheck_Out_Date() {
        return Check_Out_Date;
    }

    public void setCheck_Out_Date(Date Check_Out_Date) {
        this.Check_Out_Date = Check_Out_Date;
    }

    public void setRoom_capacity(String room_capacity) {
        this.room_capacity = room_capacity;
    }

    @Override
    public String toString() {
        return "Room{" + "roomID=" + roomID + ", room_Type=" + room_Type + ", room_capacity=" + room_capacity + ", Check_In_Date=" + Check_In_Date + ", Check_Out_Date=" + Check_Out_Date + ", isEmpty=" + isEmpty + '}';
    }

    /*==========================================================================
     ------------------------((((DataBase)))))----------------------------------
    ==========================================================================*/
    public static void RoomBooking(Guest guest, Room room, int RoomId) {
        System.out.println("in roombooking() ...");
        CheckIn(guest, room, RoomId);
        System.out.println("roombooking done ...");
    }

    public static void CheckIn(Guest guest, Room r, int RoomId) {

        System.out.println("in Checkin() ...");
        //save guest 
        System.out.println("SaveGuest() ...");
        Guest.SaveGuest(guest);

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve room based on the id: primary key
            System.out.println("\nGetting room with id: " + RoomId);

            Room room = (Room) session.get(Room.class, RoomId);

            System.out.println("Updating Room...");
            room.setIsEmpty(r.isIsEmpty());
            room.setCheck_In_Date(r.getCheck_In_Date());
            room.setCheck_Out_Date(r.getCheck_Out_Date());

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("CheckIn Done!");
        } finally {
            factory.close();
        }
    }

    public void CancelBooking(int RoomId) {
        System.out.println("in Cancel Booking() ...");
        CheckOut(RoomId);
    }

    public static int CheckOut(int RoomId) {
        System.out.println("in CheckOut() ...");
        int flag = 1;
        System.out.println("Excute deleteGuest() ... ");
        Guest.deleteGuest(RoomId);

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Room.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve room based on the id: primary key
            System.out.println("\nGetting room with id: " + RoomId);

            Room room = (Room) session.get(Room.class, RoomId);

            if (room == null) {
                return -1;
            } else {
                if (room.isIsEmpty() == true) {
                    flag = 0;
                }

                System.out.println("Updating Room...");
                room.setIsEmpty(true);
                room.setCheck_Out_Date(new Date());
            }
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("CheckOut Done!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            factory.close();
        }
        return flag;
    }

    public static Room Search_available_rooms(String RoomType, String RoomCapacity) {
        List<Room> AvailableRooms = DataBase.getAvailableRooms();
        for (int i = 0; i < AvailableRooms.size(); i++) {
            if (AvailableRooms.get(i).getRoom_Type().equals(RoomType)
                    && AvailableRooms.get(i).getRoom_capacity().equals(RoomCapacity)) {
                return AvailableRooms.get(i);
            }
        }
        return null;
    }

    public double nightCost(Room room) {
        double fees = 0;
        if ("Economy".equals(room.getRoom_Type()) && "Single".equals(room.getRoom_capacity())) {
            fees += 200;
        } else if ("Economy".equals(room.getRoom_Type()) && "Double".equals(room.getRoom_capacity())) {
            fees += 150;
        } else if ("Economy".equals(room.getRoom_Type()) && "Triple".equals(room.getRoom_capacity())) {
            fees += 100;
        } else if ("Normal".equals(room.getRoom_Type()) && "Single".equals(room.getRoom_capacity())) {
            fees += 250;
        } else if ("Normal".equals(room.getRoom_Type()) && "Double".equals(room.getRoom_capacity())) {
            fees += 200;
        } else if ("Normal".equals(room.getRoom_Type()) && "Triple".equals(room.getRoom_capacity())) {
            fees += 150;
        } else if ("Vip".equals(room.getRoom_Type()) && "Single".equals(room.getRoom_capacity())) {
            fees += 300;
        } else if ("Vip".equals(room.getRoom_Type()) && "Double".equals(room.getRoom_capacity())) {
            fees += 250;
        } else if ("Vip".equals(room.getRoom_Type()) && "Triple".equals(room.getRoom_capacity())) {
            fees += 200;
        }
        return fees;
    }

}
