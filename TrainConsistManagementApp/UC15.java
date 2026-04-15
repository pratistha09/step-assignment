class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

class GoodsBogie {
    String shape;
    String assignedCargo;

    public GoodsBogie(String shape) {
        this.shape = shape;
    }

    public void assignCargo(String cargo) {
        try {
            System.out.println("Attempting to assign " + cargo + " to " + shape + " bogie...");
            if (shape.equalsIgnoreCase("Rectangular") && cargo.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("DANGER: Petroleum cannot be carried in Rectangular bogies!");
            }
            this.assignedCargo = cargo;
            System.out.println("Success: Cargo assigned.");
        } catch (CargoSafetyException e) {
            System.out.println("Safety Violation: " + e.getMessage());
        } finally {
            System.out.println("Cargo validation process completed for this bogie.");
        }
    }
}

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        GoodsBogie bogie1 = new GoodsBogie("Cylindrical");
        bogie1.assignCargo("Petroleum");

        System.out.println();

        GoodsBogie bogie2 = new GoodsBogie("Rectangular");
        bogie2.assignCargo("Petroleum");

        System.out.println("\nSystem continues execution safely.");
    }
}
