import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Reservation {
    private final String reservationId;
    private final String customerName;
    private final String roomType;
    private final int numberOfNights;
    private final double totalAmount;

    public Reservation(String reservationId, String customerName, String roomType, int numberOfNights, double totalAmount) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.totalAmount = totalAmount;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId
                + ", Customer: " + customerName
                + ", Room Type: " + roomType
                + ", Nights: " + numberOfNights
                + ", Total Amount: Rs." + String.format("%.2f", totalAmount);
    }
}

class BookingHistory {
    private final List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addConfirmedReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return Collections.unmodifiableList(confirmedReservations);
    }
}

class BookingReportService {
    public void displayBookingHistory(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("No confirmed bookings available.");
            return;
        }

        System.out.println("\nBooking History:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public void generateSummaryReport(List<Reservation> reservations) {
        int totalBookings = reservations.size();
        int totalNights = 0;
        double totalRevenue = 0.0;

        for (Reservation reservation : reservations) {
            totalNights += reservation.getNumberOfNights();
            totalRevenue += reservation.getTotalAmount();
        }

        System.out.println("\nBooking Summary Report:");
        System.out.println("Total Confirmed Bookings: " + totalBookings);
        System.out.println("Total Nights Booked: " + totalNights);
        System.out.println("Total Revenue: Rs." + String.format("%.2f", totalRevenue));
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        System.out.print("Enter number of confirmed bookings: ");
        int bookingCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= bookingCount; i++) {
            System.out.println("\nEnter details for booking " + i + ":");

            System.out.print("Reservation ID: ");
            String reservationId = scanner.nextLine();

            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();

            System.out.print("Room Type: ");
            String roomType = scanner.nextLine();

            System.out.print("Number of Nights: ");
            int nights = scanner.nextInt();

            System.out.print("Total Amount: ");
            double totalAmount = scanner.nextDouble();
            scanner.nextLine();

            Reservation reservation = new Reservation(
                    reservationId,
                    customerName,
                    roomType,
                    nights,
                    totalAmount
            );
            bookingHistory.addConfirmedReservation(reservation);
        }

        List<Reservation> reservations = bookingHistory.getConfirmedReservations();
        reportService.displayBookingHistory(reservations);
        reportService.generateSummaryReport(reservations);
    }
}
