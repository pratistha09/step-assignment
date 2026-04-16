import java.util.Arrays;
import java.util.Scanner;

public class TrainConsistApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Train Consist Management: Bogie Name Sorting ---");
        System.out.print("Enter the number of bogies: ");
        int n = Integer.parseInt(scanner.nextLine());

        String[] bogieNames = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name for bogie " + (i + 1) + ": ");
            bogieNames[i] = scanner.nextLine();
        }

        Arrays.sort(bogieNames);

        System.out.println("\nSorted Bogie Names (Alphabetical):");
        System.out.println(Arrays.toString(bogieNames));

        System.out.println("\nProgram continues...");
        scanner.close();
    }
}
