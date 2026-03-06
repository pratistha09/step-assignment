import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
class Transaction {
    int id;
    int amount;
    String merchant;
    String account;
    LocalDateTime time;
    public Transaction(int id, int amount, String merchant, String account, String timeStr) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.account = account;
        this.time = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    @Override
    public String toString() {
        return "{id:" + id + ", amount:" + amount + ", merchant:" + merchant + ", account:" + account + ", time:" + time.format(DateTimeFormatter.ofPattern("HH:mm")) + "}";
    }
}

public class TransactionAnalyzer {
    private List<Transaction> transactions = new ArrayList<>();
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }
    public List<List<Transaction>> findTwoSum(int target) {
        Map<Integer, Transaction> map = new HashMap<>();
        List<List<Transaction>> result = new ArrayList<>();
        for (Transaction t : transactions) {
            int complement = target - t.amount;
            if (map.containsKey(complement)) {
                result.add(Arrays.asList(map.get(complement), t));
            }
            map.put(t.amount, t);
        }
        return result;
    }

    public List<List<Transaction>> findTwoSumTimeWindow(int target, Duration window) {
        Map<Integer, List<Transaction>> map = new HashMap<>();
        List<List<Transaction>> result = new ArrayList<>();
        for (Transaction t : transactions) {
            int complement = target - t.amount;
            if (map.containsKey(complement)) {
                for (Transaction c : map.get(complement)) {
                    if (Math.abs(Duration.between(t.time, c.time).toMinutes()) <= window.toMinutes()) {
                        result.add(Arrays.asList(c, t));
                    }
                }
            }
            map.computeIfAbsent(t.amount, k -> new ArrayList<>()).add(t);
        }
        return result;
    }

    public List<List<Transaction>> findKSum(int k, int target) {
        transactions.sort(Comparator.comparingInt(a -> a.amount));
        return kSumHelper(0, k, target);
    }
    private List<List<Transaction>> kSumHelper(int start, int k, int target) {
        List<List<Transaction>> res = new ArrayList<>();
        if (k == 2) {
            int left = start, right = transactions.size() - 1;
            while (left < right) {
                int sum = transactions.get(left).amount + transactions.get(right).amount;
                if (sum == target) {
                    res.add(Arrays.asList(transactions.get(left), transactions.get(right)));
                    left++;
                    right--;
                } else if (sum < target) left++;
                else right--;
            }
        } else {
            for (int i = start; i <= transactions.size() - k; i++) {
                List<List<Transaction>> temp = kSumHelper(i + 1, k - 1, target - transactions.get(i).amount);
                for (List<Transaction> tlist : temp) {
                    List<Transaction> newList = new ArrayList<>();
                    newList.add(transactions.get(i));
                    newList.addAll(tlist);
                    res.add(newList);
                }
            }
        }
        return res;
    }

    public List<Map<String, Object>> detectDuplicates() {
        Map<String, Map<Integer, Set<String>>> map = new HashMap<>();
        List<Map<String, Object>> duplicates = new ArrayList<>();
        for (Transaction t : transactions) {
            map.putIfAbsent(t.merchant, new HashMap<>());
            Map<Integer, Set<String>> amtMap = map.get(t.merchant);
            amtMap.putIfAbsent(t.amount, new HashSet<>());
            Set<String> accounts = amtMap.get(t.amount);
            accounts.add(t.account);
        }
        for (String merchant : map.keySet()) {
            Map<Integer, Set<String>> amtMap = map.get(merchant);
            for (int amount : amtMap.keySet()) {
                Set<String> accounts = amtMap.get(amount);
                if (accounts.size() > 1) {
                    Map<String, Object> dup = new HashMap<>();
                    dup.put("merchant", merchant);
                    dup.put("amount", amount);
                    dup.put("accounts", accounts);
                    duplicates.add(dup);
                }
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        TransactionAnalyzer analyzer = new TransactionAnalyzer();
        analyzer.addTransaction(new Transaction(1, 500, "Store A", "acc1", "2026-03-06 10:00"));
        analyzer.addTransaction(new Transaction(2, 300, "Store B", "acc2", "2026-03-06 10:15"));
        analyzer.addTransaction(new Transaction(3, 200, "Store C", "acc3", "2026-03-06 10:30"));
        analyzer.addTransaction(new Transaction(4, 500, "Store A", "acc2", "2026-03-06 11:00"));
        System.out.println("Classic Two-Sum target=500:");
        List<List<Transaction>> twoSum = analyzer.findTwoSum(500);
        for (List<Transaction> pair : twoSum) System.out.println(pair);
        System.out.println("\nTwo-Sum within 1 hour target=500:");
        List<List<Transaction>> twoSumWindow = analyzer.findTwoSumTimeWindow(500, Duration.ofHours(1));
        for (List<Transaction> pair : twoSumWindow) System.out.println(pair);
        System.out.println("\nK-Sum k=3 target=1000:");
        List<List<Transaction>> kSum = analyzer.findKSum(3, 1000);
        for (List<Transaction> group : kSum) System.out.println(group);
        System.out.println("\nDuplicate detection:");
        List<Map<String, Object>> duplicates = analyzer.detectDuplicates();
        for (Map<String, Object> d : duplicates) System.out.println(d);
    }
}