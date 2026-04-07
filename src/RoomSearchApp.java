import java.util.HashMap;
import java.util.Map;

// Room Domain Model
class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Amenities: " + amenities);
    }
}

// Centralized Inventory
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public Map<String, Integer> getAllAvailability() {
        return inventory;
    }
}

// Main Class
public class RoomSearchApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType("Standard", 5);
        inventory.addRoomType("Deluxe", 0);
        inventory.addRoomType("Suite", 2);

        HashMap<String, Room> rooms = new HashMap<>();

        rooms.put("Standard", new Room("Standard", 1000, "WiFi, TV"));
        rooms.put("Deluxe", new Room("Deluxe", 2000, "WiFi, TV, Mini Bar"));
        rooms.put("Suite", new Room("Suite", 4000, "WiFi, TV, Mini Bar, Living Area"));

        System.out.println("Available Rooms:\n");

        for (Map.Entry<String, Integer> entry : inventory.getAllAvailability().entrySet()) {

            String roomType = entry.getKey();
            int available = entry.getValue();

            if (available > 0) {
                Room room = rooms.get(roomType);
                room.displayDetails();
                System.out.println("Available Count: " + available);
                System.out.println("---------------------");
            }
        }
    }
}