package codd; 

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// ==========================================
// 1. MODEL - يحتوي على تفاصيل الرحلة الكاملة
// ==========================================
class Flight {
    String airline, departureTime, arrivalTime, status;

    public Flight(String airline, String dep, String arr, String status) {
        this.airline = airline;
        this.departureTime = dep;
        this.arrivalTime = arr;
        this.status = status;
    }
}

class FlightModel {
    private Map<String, Flight> flights;

    public FlightModel() {
        flights = new HashMap<>();
        // إضافة بيانات الرحلات (رقم الرحلة -> التفاصيل)
        flights.put("XY100", new Flight("Flynas", "10:00 AM", "12:30 PM", "On Time"));
        flights.put("QR500", new Flight("Qatar Airways", "03:15 PM", "06:00 PM", "Delayed"));
        flights.put("EK900", new Flight("Emirates", "09:45 PM", "11:50 PM", "Boarding"));
        flights.put("SV221", new Flight("Saudia", "01:20 AM", "04:10 AM", "Landed"));
    }

    public Flight getFlightData(String flightNumber) {
        return flights.get(flightNumber.toUpperCase());
    }
}

// ==========================================
// 2. VIEW - عرض النتيجة بشكل تفصيلي
// ==========================================
class FlightView {
    private Scanner scanner = new Scanner(System.in);

    public void displayWelcome() {
        System.out.println("========== Flight Inquiry System - Codd ==========");
    }

    public String getFlightInput() {
        System.out.print("\n[+] Enter Flight Number (e.g., XY100) or 'exit': ");
        return scanner.nextLine();
    }

    public void displayFullDetails(String flightNo, Flight f) {
        if (f != null) {
            System.out.println("\n------------------------------------------");
            System.out.println("- Flight Number:  " + flightNo.toUpperCase());
            System.out.println("- Airline:        " + f.airline);
            System.out.println("- Departure Time: " + f.departureTime);
            System.out.println("- Arrival Time:   " + f.arrivalTime);
            System.out.println("- Current Status: " + f.status);
            System.out.println("------------------------------------------");
        } else {
            System.out.println("(!) Error: Flight not found. Try XY100, QR500, or EK900.");
        }
    }
}

// ==========================================
// 3. CONTROLLER - إدارة الاستعلام
// ==========================================
class FlightController {
    private FlightModel model;
    private FlightView view;

    public FlightController(FlightModel model, FlightView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.displayWelcome();
        while (true) {
            String input = view.getFlightInput();
            if (input.equalsIgnoreCase("exit")) break;
            
            Flight data = model.getFlightData(input);
            view.displayFullDetails(input, data);
        }
    }
}

// ==========================================
// Main Class
// ==========================================
public class Codd {
    public static void main(String[] args) {
        FlightModel model = new FlightModel();
        FlightView view = new FlightView();
        FlightController controller = new FlightController(model, view);
        controller.run();
    }
}
