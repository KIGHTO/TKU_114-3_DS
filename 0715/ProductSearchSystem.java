import java.util.*;

/*
 * 測試案例：
 * 1. 完整搜尋 "keyboard" → 找到 Keyboard
 * 2. 完整搜尋 "  Mouse  " → 找到 Mouse
 * 3. 完整搜尋 "Speaker" → 查無此商品
 * 4. 部分搜尋 "o" → 多筆結果
 * 5. 最長名稱商品 → USB Cable
 * 6. 關鍵字位置 "board" → Keyboard, index 3
 */

public class ProductSearchSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int choice;
        do {
            System.out.println();
            System.out.println("=== 商品名稱搜尋系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋");
            System.out.println("3. 部分名稱搜尋");
            System.out.println("4. 顯示名稱最長的商品");
            System.out.println("5. 顯示商品名稱與搜尋關鍵字第一次出現的位置");
            System.out.println("6. 結束");
            System.out.print("請選擇功能：");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    exactNameSearch(sc, names, prices, stocks);
                    break;
                case 3:
                    partialNameSearch(sc, names, prices, stocks);
                    break;
                case 4:
                    displayLongestNameProduct(names, prices, stocks);
                    break;
                case 5:
                    findKeywordPosition(sc, names);
                    break;
                case 6:
                    System.out.println("程式結束，感謝使用");
                    break;
                default:
                    System.out.println("輸入錯誤，請輸入 1 到 6 之間的數字");
                    break;
            }

        } while (choice != 6);

        sc.close();
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("=== 全部商品 ===");
        for (int i = 0; i < names.length; i++) {
            System.out.println("編號 " + (i + 1) + "：" + names[i] + "，單價：" + prices[i] + "，庫存：" + stocks[i]);
        }
    }

    public static void exactNameSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入完整商品名稱：");
        String keyword = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(keyword)) {
                System.out.println("商品名稱：" + names[i]);
                System.out.println("單價：" + prices[i]);
                System.out.println("庫存：" + stocks[i]);
                found = true;
                break;
            }
        }

        if (found == false) {
            System.out.println("查無此商品");
        }
    }

    public static void partialNameSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入要搜尋的部分名稱：");
        String keyword = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.println("=== 搜尋結果 ===");
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(keyword)) {
                System.out.println(names[i] + "，單價：" + prices[i] + "，庫存：" + stocks[i]);
                found = true;
            }
        }

        if (found == false) {
            System.out.println("查無符合的商品");
        }
    }

    public static void displayLongestNameProduct(String[] names, int[] prices, int[] stocks) {
        int longestIndex = 0;

        for (int i = 0; i < names.length; i++) {
            if (names[i].length() > names[longestIndex].length()) {
                longestIndex = i;
            }
        }

        System.out.println("名稱最長的商品是：" + names[longestIndex]
                + "（" + names[longestIndex].length() + " 個字元）");
        System.out.println("單價：" + prices[longestIndex] + "，庫存：" + stocks[longestIndex]);
    }

    public static void findKeywordPosition(Scanner sc, String[] names) {
        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("關鍵字不可以是空白");
            return;
        }

        String lowerKeyword = keyword.toLowerCase();
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            int position = names[i].toLowerCase().indexOf(lowerKeyword);

            if (position != -1) {
                System.out.println(names[i] + " → 第一次出現位置：index " + position);
                found = true;
            }
        }

        if (found == false) {
            System.out.println("所有商品名稱皆查無此關鍵字");
        }
    }
}

