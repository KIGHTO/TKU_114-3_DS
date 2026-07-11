import java.util.*;

public class QuantityValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int quantity = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("請輸入商品數量: ");
            quantity = sc.nextInt();

            if (quantity > 0) {
                valid = true;
            } else {
                System.out.println("數量必須大於 0，請重新輸入。");
            }
        }

        System.out.println("輸入的數量: " + quantity);

        sc.close();
    }
}
