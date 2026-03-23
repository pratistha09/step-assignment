import java.util.*;

class Client {
    String name;
    int risk;
    int balance;

    Client(String n, int r, int b) {
        name = n; risk = r; balance = b;
    }

    public String toString() {
        return name + ":" + risk;
    }
}

public class p2 {

    static void bubbleSort(Client[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j].risk > arr[j + 1].risk) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].risk < key.risk) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {

        Client[] arr = {
                new Client("C", 80, 1000),
                new Client("A", 20, 2000),
                new Client("B", 50, 1500)
        };

        bubbleSort(arr);
        System.out.println("Bubble Asc: " + Arrays.toString(arr));

        insertionSort(arr);
        System.out.println("Insertion Desc: " + Arrays.toString(arr));

        System.out.println("Top Risk: " + arr[0]);
    }
}