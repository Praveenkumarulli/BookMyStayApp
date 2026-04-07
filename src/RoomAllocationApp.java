import java.util.*;

// Reservation representing booking intent
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

// Centralized Inventory
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public boolean decrementAvailability(String roomType) {
        int available = getAvailability(roomType);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        } else {
            return false;
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}

// Booking Service: Allocates rooms safely
class BookingService {
    private RoomInventory inventory;
    private Queue<Reservation> requestQueue;
    private Map<String, Set<String>> allocatedRooms; // roomType -> assigned room IDs
    private int roomIdCounter;

    public BookingService(RoomInventory inventory, Queue<Reservation> requestQueue) {
        this.inventory = inventory;
        this.requestQueue = requestQueue;
        this.allocatedRooms = new HashMap<>();
        this.roomIdCounter = 100; // start room IDs from 100
    }

    // Process all requests
    public void processRequests() {
        while (!requestQueue.isEmpty()) {
            Reservation r = requestQueue.poll();
            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {
                String roomId = generateRoomId(type);
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                inventory.decrementAvailability(type);

                System.out.println("Reservation confirmed for " + r.getGuestName() +
                        " | Room Type: " + type +
                        " | Assigned Room ID: " + roomId);
            } else {
                System.out.println("Cannot allocate " + type + " to " + r.getGuestName() +
                        " | Room unavailable");
            }
        }
    }

    private String generateRoomId(String roomType) {
        roomIdCounter++;
        return roomType.substring(0, 1).toUpperCase() + roomIdCounter;
    }

    public void displayAllocatedRooms() {
        System.out.println("\nAllocated Rooms:");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main Class
public class RoomAllocationApp {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Standard", 2);
        inventory.addRoomType("Deluxe", 1);
        inventory.addRoomType("Suite", 1);

        // Create booking request queue
        Queue<Reservation> requestQueue = new LinkedList<>();
        requestQueue.offer(new Reservation("Alice", "Standard"));
        requestQueue.offer(new Reservation("Bob", "Deluxe"));
        requestQueue.offer(new Reservation("Charlie", "Standard"));
        requestQueue.offer(new Reservation("David", "Suite"));
        requestQueue.offer(new Reservation("Eve", "Deluxe")); // overbooking scenario

        // Process bookings
        BookingService service = new BookingService(inventory, requestQueue);
        service.processRequests();

        // Display final allocation and inventory
        service.displayAllocatedRooms();
        inventory.displayInventory();
    }
}