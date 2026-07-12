import java.util.*;

public class AtmSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額: ");
                    balance = deposit(balance, depositAmount);
                    depositCount++;
                    totalDeposit = totalDeposit + depositAmount;
                    printBalance(balance);
                    break;

                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");

                    while (!canWithdraw(balance, withdrawAmount)) {
                        System.out.println("提款金額不能超過目前餘額，請重新輸入。");
                        withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");
                    }

                    balance = withdraw(balance, withdrawAmount);
                    withdrawCount++;
                    totalWithdraw = totalWithdraw + withdrawAmount;
                    printBalance(balance);
                    break;

                case 4:
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }

        printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(message);
            amount = sc.nextInt();

            if (amount > 0) {
                valid = true;
            } else {
                System.out.println("金額必須大於 0，請重新輸入。");
            }
        }

        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static boolean canWithdraw(int balance, int amount) {
        if (amount <= balance) {
            return true;
        } else {
            return false;
        }
    }

    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println();
        System.out.println("=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
        System.out.println();
    }
}
