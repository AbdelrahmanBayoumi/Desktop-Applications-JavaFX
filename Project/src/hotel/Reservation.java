package hotel;
public class Reservation {
    
    private Room room;
    private Guest guest;

    public Reservation(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    
    
}
