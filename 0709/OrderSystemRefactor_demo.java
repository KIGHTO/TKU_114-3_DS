import java.util.*;

public class OrderSystemRefactor_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalItems = 0;
        int totalAmount = 0;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請選擇商品: ");
            option = sc.nextInt();

            if (option == 1 || option == 2 || option == 3) {
                int price = getPrice(option);
                int quantity = readValidQuantity(sc);
                int subtotal = calculateSubtotal(price, quantity);

                System.out.println("本次小計: " + subtotal);

                totalItems = totalItems + quantity;
                totalAmount = totalAmount + subtotal;
            } else if (option != 0) {
                System.out.println("無效的選項，請重新輸入。");
            }
        }

        printReceipt(totalItems, totalAmount);
        sc.close();
    }

    public static void printMenu() {
        System.out.println("1: Black tea  30");
        System.out.println("2: Green tea  35");
        System.out.println("3: Coffee     50");
        System.out.println("0: Checkout");
    }

    public static int getPrice(int option) {
        int price = 0;

        switch (option) {
            case 1:
                price = 30;
                break;
            case 2:
                price = 35;
                break;
            case 3:
                price = 50;
                break;
        }

        return price;
    }

    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("請輸入數量: ");
            quantity = sc.nextInt();

            if (quantity > 0) {
                valid = true;
            } else {
                System.out.println("數量必須大於 0，請重新輸入。");
            }
        }

        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("========================");
        System.out.println("        收據");
        System.out.println("========================");
        System.out.println("總數量: " + totalItems);
        System.out.println("總金額: " + totalAmount);
        System.out.println("========================");
        System.out.println("感謝您的購買！");
    }
}
