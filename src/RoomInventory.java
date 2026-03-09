import java.util.HashMap;
import java.util.Map;

class bookmystayapp {

    // Centralized inventory storage
    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    public bookmystayapp() {
        inventory = new HashMap<>();
    }

    // Register a new room type with available count
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability in a controlled way
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found in inventory.");
        }
    }

    // Display entire inventory
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}