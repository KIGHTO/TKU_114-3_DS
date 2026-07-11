import java.util.*;

public class AtmMenu {
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
                    checkBalance(balance);
                    break;

                case 2:
                    balance = deposit(sc, balance);
                    break;

                case 3:
                    balance = withdraw(sc, balance);
                    break;

                case 0:
                    System.out.println("感謝使用，再見。");
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }
    }

    public static void printMenu() {
        System.out.println("1: 查詢餘額");
        System.out.println("2: 存款");
        System.out.println("3: 提款");
        System.out.println("0: 離開");
    }

    public static void checkBalance(int balance) {
        System.out.println("目前餘額: " + balance);
    }

    public static int deposit(Scanner sc, int balance) {
        int amount = readValidAmount(sc, "請輸入存款金額: ");
        balance = balance + amount;
        System.out.println("存款成功，目前餘額: " + balance);
        return balance;
    }

    public static int withdraw(Scanner sc, int balance) {
        int amount = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("請輸入提款金額: ");
            amount = sc.nextInt();

            if (amount <= 0) {
                System.out.println("提款金額必須大於 0，請重新輸入。");
            } else if (amount > balance) {
                System.out.println("提款金額不能大於目前餘額，請重新輸入。");
            } else {
                valid = true;
            }
        }

        balance = balance - amount;
        System.out.println("提款成功，目前餘額: " + balance);
        return balance;
    }

    public static int readValidAmount(Scanner sc, String message) {
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
        sc.close();

        return amount;

       
    }
    
}
