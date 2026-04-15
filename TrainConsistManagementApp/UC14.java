class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

class PassengerBogie {
    String type;
    int capacity;

    public PassengerBogie(String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return type + " (" + capacity + " seats)";
    }
}

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        try {
            System.out.println("Creating valid bogie...");
            PassengerBogie validBogie = new PassengerBogie("Sleeper", 72);
            System.out.println("Successfully created: " + validBogie);

            System.out.println("\nAttempting to create invalid bogie (Zero Capacity)...");
            PassengerBogie invalidBogie1 = new PassengerBogie("AC Chair", 0);
        } catch (InvalidCapacityException e) {
            System.out.println("Validation Failed: " + e.getMessage());
        }

        try {
            System.out.println("\nAttempting to create invalid bogie (Negative Capacity)...");
            PassengerBogie invalidBogie2 = new PassengerBogie("First Class", -10);
        } catch (InvalidCapacityException e) {
            System.out.println("Validation Failed: " + e.getMessage());
        }
        
        System.out.println("\nSystem continues execution safely.");
    }
}
