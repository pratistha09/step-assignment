import java.util.ArrayList;
import java.util.List;

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();

        System.out.println("Initializing train consist...");
        
        int initialCount = trainConsist.size();
        System.out.println("Current Bogie Count: " + initialCount);

        System.out.println("System ready. No bogies attached yet.");
    }
}
