import java.util.ArrayList;
import java.util.List;

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();

        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("After adding bogies: " + trainConsist);

        trainConsist.remove("AC Chair");
        System.out.println("After removing AC Chair: " + trainConsist);

        boolean hasSleeper = trainConsist.contains("Sleeper");
        System.out.println("Does Sleeper bogie exist? " + hasSleeper);

        System.out.println("Final Train Consist: " + trainConsist);
        System.out.println("Final Bogie Count: " + trainConsist.size());
    }
}
