import java.util.*;

public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;
        int totalItems = 0;
        int totalAmount = 0;

        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項: ");
            option = sc.nextInt();

            if (option == 1 || option == 2 || option == 3 || option == 4) {
                int quantity = readValidQuantity(sc);
                int price = getPrice(option);
                int subtotal = calculateSubtotal(price, quantity);

                System.out.println(getItemName(option) + " x " + quantity);
                System.out.println("Subtotal: " + subtotal);
                System.out.println();

                totalItems = totalItems + quantity;
                totalAmount = totalAmount + subtotal;

                switch (option) {
                    case 1:
                        blackTeaCount = blackTeaCount + quantity;
                        break;
                    case 2:
                        greenTeaCount = greenTeaCount + quantity;
                        break;
                    case 3:
                        milkTeaCount = milkTeaCount + quantity;
                        break;
                    case 4:
                        coffeeCount = coffeeCount + quantity;
                        break;
                }
            } else if (option != 0) {
                System.out.println("無效的選項，請重新輸入。");
            }
        }

        int finalAmount = calculateDiscountedTotal(totalAmount);

        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, finalAmount);

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
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
                price = 45;
                break;
            case 4:
                price = 50;
                break;
        }

        return price;
    }

    public static String getItemName(int option) {
        String name = "";

        switch (option) {
            case 1:
                name = "Black tea";
                break;
            case 2:
                name = "Green tea";
                break;
            case 3:
                name = "Milk tea";
                break;
            case 4:
                name = "Coffee";
                break;
        }

        return name;
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

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        } else {
            return totalAmount;
        }
    }

    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount, int finalAmount) {
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);

        if (finalAmount < totalAmount) {
            System.out.println("Discount: Yes");
        } else {
            System.out.println("Discount: No");
        }

        System.out.println("Final amount: " + finalAmount);
    }
}
