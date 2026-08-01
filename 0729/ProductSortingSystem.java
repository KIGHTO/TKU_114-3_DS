import java.util.Scanner;

public class ProductSortingSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StoreProduct[] originalProducts = {
            new StoreProduct("P01", "滑鼠", 299, 50),
            new StoreProduct("P02", "鍵盤", 590, 30),
            new StoreProduct("P03", "螢幕", 3200, 10),
            new StoreProduct("P04", "耳機", 590, 25),
            new StoreProduct("P05", "隨身碟", 199, 100),
            new StoreProduct("P06", "滑鼠墊", 199, 80),
            new StoreProduct("P07", "網路攝影機", 890, 15),
            new StoreProduct("P08", "喇叭", 590, 12),
            new StoreProduct("P09", "行動電源", 690, 45),
            new StoreProduct("P10", "傳輸線", 149, 200)
        };

        while (true) {
            System.out.println("========== 商品排序選單 ==========");
            System.out.println("1. 依價格升冪排序");
            System.out.println("2. 依價格降冪排序");
            System.out.println("3. 依庫存降冪排序");
            System.out.println("4. 離開系統");
            System.out.print("請選擇功能：");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                StoreProduct[] workingCopy = copyProducts(originalProducts);
                insertionSortByPrice(workingCopy, true);
                printResult(workingCopy, "價格", "升冪");
            } else if (choice.equals("2")) {
                StoreProduct[] workingCopy = copyProducts(originalProducts);
                insertionSortByPrice(workingCopy, false);
                printResult(workingCopy, "價格", "降冪");
            } else if (choice.equals("3")) {
                StoreProduct[] workingCopy = copyProducts(originalProducts);
                insertionSortByStock(workingCopy);
                printResult(workingCopy, "庫存", "降冪");
            } else if (choice.equals("4")) {
                System.out.println("系統已結束。");
                break;
            } else {
                System.out.println("輸入錯誤，請重新輸入。");
            }
        }

        sc.close();
    }

    public static StoreProduct[] copyProducts(StoreProduct[] source) {
        StoreProduct[] copy = new StoreProduct[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].copy();
        }
        return copy;
    }

    public static void insertionSortByPrice(StoreProduct[] products, boolean ascending) {
        for (int i = 1; i < products.length; i++) {
            StoreProduct key = products[i];
            int position = i - 1;

            while (position >= 0 && shouldShiftByPrice(products[position], key, ascending)) {
                products[position + 1] = products[position];
                position--;
            }

            products[position + 1] = key;
        }
    }

    public static boolean shouldShiftByPrice(StoreProduct a, StoreProduct key, boolean ascending) {
        if (ascending) {
            return a.getPrice() > key.getPrice();
        } else {
            return a.getPrice() < key.getPrice();
        }
    }

    public static void insertionSortByStock(StoreProduct[] products) {
        for (int i = 1; i < products.length; i++) {
            StoreProduct key = products[i];
            int position = i - 1;

            while (position >= 0 && products[position].getStock() < key.getStock()) {
                products[position + 1] = products[position];
                position--;
            }

            products[position + 1] = key;
        }
    }

    public static void printResult(StoreProduct[] products, String field, String direction) {
        System.out.println("========== 排序欄位：" + field + "，排序方向：" + direction + " ==========");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].toDisplayString());
        }
        System.out.println();
    }
}
