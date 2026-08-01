public class TransactionSortingSystem {

    public static void main(String[] args) {

        Transaction[] transactions = {
            new Transaction("T01", "A001", 1500, 3),
            new Transaction("T02", "A002", 2800, 1),
            new Transaction("T03", "A003", 900, 5),
            new Transaction("T04", "A004", 2800, 2),
            new Transaction("T05", "A005", 4200, 4),
            new Transaction("T06", "A006", 900, 7),
            new Transaction("T07", "A007", 3600, 6),
            new Transaction("T08", "A008", 1500, 8)
        };

        System.out.println("========== 排序前 ==========");
        printTransactions(transactions);

        insertionSort(transactions);

        System.out.println("========== 排序後（金額降冪，相同金額依時間序號升冪） ==========");
        printTransactions(transactions);
    }

    public static void insertionSort(Transaction[] transactions) {
        for (int i = 1; i < transactions.length; i++) {
            Transaction key = transactions[i];
            int position = i - 1;

            while (position >= 0 && isLowerPriority(transactions[position], key)) {
                transactions[position + 1] = transactions[position];
                position--;
            }

            transactions[position + 1] = key;
        }
    }

    public static boolean isLowerPriority(Transaction a, Transaction key) {
        if (a.getAmount() != key.getAmount()) {
            return a.getAmount() < key.getAmount();
        } else {
            return a.getTimeSerial() > key.getTimeSerial();
        }
    }

    public static void printTransactions(Transaction[] transactions) {
        for (int i = 0; i < transactions.length; i++) {
            System.out.println(transactions[i].toDisplayString());
        }
        System.out.println();
    }
}
