import java.util.LinkedList;
import java.util.Queue;

// Reservation class representing a booking request
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

// Booking Request Queue Manager
class BookingRequestQueue {

    private Queue<Reservation> bookingQueue;

    public BookingRequestQueue() {
        bookingQueue = new LinkedList<>();
    }

    // Add booking request to queue
    public void addRequest(Reservation reservation) {
        bookingQueue.offer(reservation);
        System.out.println("Booking request received from " + reservation.getGuestName());
    }

    // Display all queued requests
    public void displayQueue() {

        System.out.println("\nCurrent Booking Requests (FIFO Order):");

        for (Reservation r : bookingQueue) {
            r.displayReservation();
        }
    }
}

// Main Class
public class BookingRequestQueueApp {

    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Guests submit booking requests
        requestQueue.addRequest(new Reservation("Alice", "Standard"));
        requestQueue.addRequest(new Reservation("Bob", "Deluxe"));
        requestQueue.addRequest(new Reservation("Charlie", "Suite"));
        requestQueue.addRequest(new Reservation("David", "Standard"));

        // Display queued requests
        requestQueue.displayQueue();
    }
}