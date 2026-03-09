abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: $" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 100.0);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 180.0);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 350.0);
    }
}

public class RoomInitialization {
    public static void main(String[] args) {

        System.out.println("Book My Stay App");
        System.out.println("Version: 2.1");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 10;
        int doubleAvailability = 5;
        int suiteAvailability = 2;

        System.out.println();
        single.displayDetails();
        System.out.println("Available Rooms: " + singleAvailability);

        System.out.println();
        doubleRoom.displayDetails();
        System.out.println("Available Rooms: " + doubleAvailability);

        System.out.println();
        suite.displayDetails();
        System.out.println("Available Rooms: " + suiteAvailability);
    }
}