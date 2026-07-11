import java.util.*;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請選擇: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額: ");
                    balance = deposit(balance, depositAmount);
                    System.out.println("存款成功，目前餘額: " + balance);
                    break;

                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");

                    while (withdrawAmount > balance) {
                        System.out.println("提款金額不能大於目前餘額，請重新輸入。");
                        withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");
                    }

                    balance = withdraw(balance, withdrawAmount);
                    System.out.println("提款成功，目前餘額: " + balance);
                    break;

                case 0:
                    System.out.println("感謝使用，再見。");
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("1: 查詢餘額");
        System.out.println("2: 存款");
        System.out.println("3: 提款");
        System.out.println("0: 離開");
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

    public static void printBalance(int balance) {
        System.out.println("目前餘額: " + balance);
    }
}
