import java.util.*;

class Asset {
    String name;
    int returnRate;

    Asset(String n, int r) { name = n; returnRate = r; }

    public String toString() {
        return name + ":" + returnRate;
    }
}

public class p4 {

    static void mergeSort(List<Asset> list) {
        list.sort(Comparator.comparingInt(a -> a.returnRate));
    }

    static void quickSort(List<Asset> list) {
        list.sort((a, b) -> b.returnRate - a.returnRate);
    }

    public static void main(String[] args) {

        List<Asset> list = new ArrayList<>();
        list.add(new Asset("AAPL", 12));
        list.add(new Asset("TSLA", 8));
        list.add(new Asset("GOOG", 15));

        mergeSort(list);
        System.out.println("Merge: " + list);

        quickSort(list);
        System.out.println("Quick: " + list);
    }
}