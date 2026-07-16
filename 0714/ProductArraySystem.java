import java.util.*;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int purchaseCount = 0;
        int restockCount = 0;
        int totalRevenue = 0;

        int choice;
        do {
            System.out.println();
            System.out.println("=== 商品陣列管理系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依商品編號查詢");
            System.out.println("3. 購買商品並扣除庫存");
            System.out.println("4. 補充商品庫存");
            System.out.println("5. 顯示低庫存商品");
            System.out.println("6. 顯示全部庫存總價值");
            System.out.println("7. 結束並顯示操作摘要");
            System.out.print("請選擇功能：");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProductByCode(sc, names, prices, stocks);
                    break;
                case 3:
                    int revenue = purchaseProduct(sc, names, prices, stocks);
                    if (revenue > 0) {
                        purchaseCount = purchaseCount + 1;
                        totalRevenue = totalRevenue + revenue;
                    }
                    break;
                case 4:
                    boolean restocked = restockProduct(sc, names, stocks);
                    if (restocked) {
                        restockCount = restockCount + 1;
                    }
                    break;
                case 5:
                    displayLowStock(names, stocks);
                    break;
                case 6:
                    int totalValue = calculateTotalStockValue(prices, stocks);
                    System.out.println("全部庫存總價值：" + totalValue);
                    break;
                case 7:
                    displaySummary(purchaseCount, restockCount, totalRevenue);
                    break;
                default:
                    System.out.println("輸入錯誤，請輸入 1 到 7 之間的數字");
                    break;
            }

        } while (choice != 7);

        sc.close();
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("=== 全部商品 ===");
        for (int i = 0; i < names.length; i++) {
            System.out.println("編號 " + (i + 1) + "：" + names[i] + "，單價：" + prices[i] + "，庫存：" + stocks[i]);
        }
    }

    public static void queryProductByCode(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入商品編號：");
        int code = sc.nextInt();
        int index = code - 1;

        if (index >= 0 && index < names.length) {
            System.out.println("商品名稱：" + names[index]);
            System.out.println("單價：" + prices[index]);
            System.out.println("庫存：" + stocks[index]);
        } else {
            System.out.println("查無此商品編號");
        }
    }

    public static int purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入商品編號：");
        int code = sc.nextInt();
        int index = code - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("查無此商品編號");
            return -1;
        }

        System.out.print("請輸入購買數量：");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("購買數量必須大於 0");
            return -1;
        }

        if (quantity > stocks[index]) {
            System.out.println("庫存不足，目前庫存為：" + stocks[index]);
            return -1;
        }

        stocks[index] = stocks[index] - quantity;
        int revenue = quantity * prices[index];
        System.out.println("購買成功！" + names[index] + " 已扣除庫存 " + quantity + " 件，花費：" + revenue);
        return revenue;
    }

    public static boolean restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入商品編號：");
        int code = sc.nextInt();
        int index = code - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("查無此商品編號");
            return false;
        }

        System.out.print("請輸入補貨數量：");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("補貨數量必須大於 0");
            return false;
        }

        stocks[index] = stocks[index] + quantity;
        System.out.println("補貨成功！" + names[index] + " 目前庫存為：" + stocks[index]);
        return true;
    }

    public static void displayLowStock(String[] names, int[] stocks) {
        System.out.println("=== 低庫存商品（庫存小於 10）===");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println(names[i] + " 庫存：" + stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品");
        }
    }

    public static int calculateTotalStockValue(int[] prices, int[] stocks) {
        int total = 0;
        for (int i = 0; i < prices.length; i++) {
            total = total + prices[i] * stocks[i];
        }
        return total;
    }

    public static void displaySummary(int purchaseCount, int restockCount, int totalRevenue) {
        System.out.println("=== 操作摘要 ===");
        System.out.println("購買次數：" + purchaseCount);
        System.out.println("補貨次數：" + restockCount);
        System.out.println("總營收：" + totalRevenue);
        System.out.println("程式結束，感謝使用");
    }
}
