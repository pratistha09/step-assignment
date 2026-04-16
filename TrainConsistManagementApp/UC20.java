import java.util.Arrays;
import java.util.Scanner;

public class TrainConsistApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Train Consist Management: Search Validation ---");
        System.out.print("Enter the number of bogies (Enter 0 to test exception): ");
        int n = Integer.parseInt(scanner.nextLine());

        String[] bogieIds = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID for bogie " + (i + 1) + ": ");
            bogieIds[i] = scanner.nextLine();
        }

        System.out.print("\nEnter Bogie ID to search for: ");
        String searchKey = scanner.nextLine();

        try {
            validateAndSearch(bogieIds, searchKey);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nProgram continues...");
        scanner.close();
    }

    public static void validateAndSearch(String[] bogieIds, String searchKey) {
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("Cannot perform search: The train consist is empty.");
        }

        Arrays.sort(bogieIds);
        int result = Arrays.binarySearch(bogieIds, searchKey);

        if (result >= 0) {
            System.out.println("Result: Bogie " + searchKey + " found.");
        } else {
            System.out.println("Result: Bogie " + searchKey + " not found.");
        }
    }
}
