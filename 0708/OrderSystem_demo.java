import java.util.*;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalQty = 0;
        int totalPrice = 0;
        int option = -1;

        while (option!=0) {
            System.out.println("1: Black tea  30");
            System.out.println("2: Green tea  35");
            System.out.println("3: Coffee     50");
            System.out.println("0: Checkout");
            System.out.print("請選擇商品: ");
            option = sc.nextInt();

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
                case 0:
                    price = 0;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }

            if (option == 1 || option == 2 || option == 3) {
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

                int subtotal = price * quantity;
                System.out.println("本次小計: " + subtotal);

                totalQty = totalQty + quantity;
                totalPrice = totalPrice + subtotal;
            }
        }

        System.out.println("總數量: " + totalQty);
        System.out.println("總金額: " + totalPrice);

        sc.close();
    }
}

