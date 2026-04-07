import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingInventory {
    private final Map<String, Integer> roomAvailability;

    public BookingInventory() {
        roomAvailability = new LinkedHashMap<>();
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 1);
    }

    public boolean hasRoomType(String roomType) {
        return roomAvailability.containsKey(roomType);
    }

    public int getAvailableRooms(String roomType) {
        Integer available = roomAvailability.get(roomType);
        return available == null ? 0 : available;
    }

    public void reserveRoom(String roomType) throws InvalidBookingException {
        if (!hasRoomType(roomType)) {
            throw new InvalidBookingException(
                    "Invalid room type. Use exactly one of: Single, Double, Suite."
            );
        }

        int available = roomAvailability.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for room type: " + roomType);
        }

        roomAvailability.put(roomType, available - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Room Availability:");
        for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class InvalidBookingValidator {
    public void validateGuestName(String guestName) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }
    }

    public void validateRoomType(String roomType, BookingInventory inventory) throws InvalidBookingException {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty.");
        }

        if (!inventory.hasRoomType(roomType)) {
            throw new InvalidBookingException(
                    "Invalid room type. Use exactly one of: Single, Double, Suite."
            );
        }
    }

    public void validateNights(int nights) throws InvalidBookingException {
        if (nights <= 0) {
            throw new InvalidBookingException("Number of nights must be greater than 0.");
        }
    }
}

class BookingService {
    private final InvalidBookingValidator validator;
    private final BookingInventory inventory;

    public BookingService(InvalidBookingValidator validator, BookingInventory inventory) {
        this.validator = validator;
        this.inventory = inventory;
    }

    public void processBooking(String guestName, String roomType, int nights) throws InvalidBookingException {
        validator.validateGuestName(guestName);
        validator.validateRoomType(roomType, inventory);
        validator.validateNights(nights);

        inventory.reserveRoom(roomType);

        System.out.println("Booking confirmed for " + guestName
                + ". Room Type: " + roomType
                + ", Nights: " + nights);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingInventory inventory = new BookingInventory();
        InvalidBookingValidator validator = new InvalidBookingValidator();
        BookingService bookingService = new BookingService(validator, inventory);

        System.out.print("Enter number of booking requests: ");
        int requestCount;

        try {
            requestCount = Integer.parseInt(scanner.nextLine().trim());
            if (requestCount <= 0) {
                System.out.println("Failure: Number of booking requests must be greater than 0.");
                return;
            }
        } catch (NumberFormatException exception) {
            System.out.println("Failure: Enter a valid integer for number of booking requests.");
            return;
        }

        for (int i = 1; i <= requestCount; i++) {
            System.out.println("\nBooking Request " + i + ":");

            try {
                System.out.print("Enter guest name: ");
                String guestName = scanner.nextLine();

                System.out.print("Enter room type (Single/Double/Suite): ");
                String roomType = scanner.nextLine();

                System.out.print("Enter number of nights: ");
                int nights = Integer.parseInt(scanner.nextLine().trim());

                bookingService.processBooking(guestName, roomType, nights);
            } catch (NumberFormatException exception) {
                System.out.println("Failure: Number of nights must be a valid integer.");
            } catch (InvalidBookingException exception) {
                System.out.println("Failure: " + exception.getMessage());
            }

            inventory.displayInventory();
        }

        System.out.println("\nSystem remained stable after processing all booking requests.");
    }
}
