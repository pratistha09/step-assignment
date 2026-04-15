import java.util.LinkedList;

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        LinkedList<String> trainConsist = new LinkedList<>();

        trainConsist.add("Engine");
        trainConsist.add("Sleeper");
        trainConsist.add("AC");
        trainConsist.add("Cargo");
        trainConsist.add("Guard");

        System.out.println("Initial Sequence: " + trainConsist);

        trainConsist.add(2, "Pantry Car");
        System.out.println("After adding Pantry Car at position 2: " + trainConsist);

        trainConsist.removeFirst();
        trainConsist.removeLast();

        System.out.println("Final Ordered Consist: " + trainConsist);
    }
}
