import java.util.Arrays;
import java.util.Scanner;

public class TrainConsistApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.util.in);

        System.out.println("--- Train Consist Management: Binary Search for Bogie ID ---");
        System.out.print("Enter the number of bogies: ");
        int n = Integer.parseInt(scanner.nextLine());

        String[] bogieIds = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID for bogie " + (i + 1) + ": ");
            bogieIds[i] = scanner.nextLine();
        }

        Arrays.sort(bogieIds);
        System.out.println("\nSorted IDs for searching: " + Arrays.toString(bogieIds));

        System.out.print("Enter Bogie ID to search for: ");
        String searchKey = scanner.nextLine();

        boolean isFound = performBinarySearch(bogieIds, searchKey);

        if (isFound) {
            System.out.println("Result: Bogie " + searchKey + " found successfully.");
        } else {
            System.out.println("Result: Bogie " + searchKey + " not found.");
        }

        System.out.println("\nProgram continues...");
        scanner.close();
    }

    public static boolean performBinarySearch(String[] arr, String key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = key.compareTo(arr[mid]);

            if (comparison == 0) {
                return true;
            } else if (comparison > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
