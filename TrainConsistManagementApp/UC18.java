import java.util.Scanner;

public class TrainConsistApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Train Consist Management: Linear Search for Bogie ID ---");
        System.out.print("Enter the number of bogies in the consist: ");
        int n = Integer.parseInt(scanner.nextLine());

        String[] bogieIds = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID for bogie " + (i + 1) + ": ");
            bogieIds[i] = scanner.nextLine();
        }

        System.out.print("\nEnter Bogie ID to search for: ");
        String searchKey = scanner.nextLine();

        // UC18: Linear Search Logic
        boolean isFound = false;
        for (int i = 0; i < bogieIds.length; i++) {
            // Sequential traversal and equality comparison
            if (bogieIds[i].equals(searchKey)) {
                isFound = true;
                break; // Early termination once match is found
            }
        }

        if (isFound) {
            System.out.println("Result: Bogie " + searchKey + " found in the consist.");
        } else {
            System.out.println("Result: Bogie " + searchKey + " not found.");
        }

        System.out.println("\nProgram continues...");
        scanner.close();
    }
}
