import java.util.Scanner;

public class TrainConsistApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Train Consist Management: Passenger Bogie Sorting ---");
        System.out.print("Enter the number of passenger bogies: ");
        int n = scanner.nextInt();

        int[] capacities = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter capacity for bogie " + (i + 1) + ": ");
            capacities[i] = scanner.nextInt();
        }

        bubbleSort(capacities);

        System.out.println("\nSorted Passenger Bogie Capacities (Ascending):");
        for (int capacity : capacities) {
            System.out.print(capacity + " ");
        }
        System.out.println("\n\nProgram continues...");
        scanner.close();
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
