import java.util.ArrayList;
import java.util.List;

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return type + " [" + cargo + "]";
    }
}

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Rectangular", "Coal"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Grain"));

        System.out.println("Current Goods Consist: " + goodsBogies);

        boolean isSafe = goodsBogies.stream().allMatch(b -> {
            if (b.type.equalsIgnoreCase("Cylindrical")) {
                return b.cargo.equalsIgnoreCase("Petroleum");
            }
            return true;
        });

        System.out.println("Safety Compliance Check: " + (isSafe ? "PASS" : "FAIL"));

        if (isSafe) {
            System.out.println("Status: The train is safe for departure.");
        } else {
            System.out.println("Status: DANGER! Unauthorized cargo detected in cylindrical bogies.");
        }
    }
}
