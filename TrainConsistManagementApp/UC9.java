import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + capacity + " seats)";
    }
}

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("AC Chair", 56));

        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));

        System.out.println("Grouped Bogie Summary:");
        groupedBogies.forEach((type, list) -> {
            System.out.println("Type: " + type + " | Count: " + list.size() + " | Details: " + list);
        });
        
        System.out.println("Original List Size: " + bogies.size());
    }
}
