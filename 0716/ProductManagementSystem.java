import java.util.*;

public class ProductManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = new Product[10];
        int productCount = initializeProducts(products);

        // 操作次數統計，用於最後的操作摘要
        int addCount = 0;
        int sellCount = 0;
        int restockCount = 0;
        int priceChangeCount = 0;
        int searchCount = 0;

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇功能：");
            String input = sc.nextLine();
            int choice = parseMenuChoice(input);

            if (choice == 1) {
                displayAllProducts(products, productCount);
            } else if (choice == 2) {
                searchByName(sc, products, productCount);
                searchCount++;
            } else if (choice == 3) {
                boolean success = addProduct(sc, products, productCount);
                if (success) {
                    productCount++;
                    addCount++;
                }
            } else if (choice == 4) {
                boolean success = sellProduct(sc, products, productCount);
                if (success) {
                    sellCount++;
                }
            } else if (choice == 5) {
                boolean success = restockProduct(sc, products, productCount);
                if (success) {
                    restockCount++;
                }
            } else if (choice == 6) {
                boolean success = modifyPrice(sc, products, productCount);
                if (success) {
                    priceChangeCount++;
                }
            } else if (choice == 7) {
                displayLowStock(products, productCount);
            } else if (choice == 8) {
                displayTotalInventoryValue(products, productCount);
            } else if (choice == 9) {
                displaySummary(addCount, sellCount, restockCount, priceChangeCount, searchCount);
                running = false;
            } else {
                System.out.println("輸入錯誤，請輸入 1 到 9 之間的數字。");
            }

            System.out.println();
        }

        sc.close();
    }

    
    public static void printMenu() {
        System.out.println("========== 商品管理系統 ==========");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
        System.out.println("===================================");
    }

   
    public static int parseMenuChoice(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    
    public static int initializeProducts(Product[] products) {
        products[0] = new Product("Keyboard", 890, 20);
        products[1] = new Product("Mouse", 450, 35);
        products[2] = new Product("Monitor", 3990, 8);
        products[3] = new Product("Headset", 1290, 15);
        products[4] = new Product("Webcam", 990, 6);
        return 5;
    }

    
    public static void displayAllProducts(Product[] products, int productCount) {
        if (productCount == 0) {
            System.out.println("目前沒有任何商品。");
            return;
        }

        System.out.println("---------- 全部商品 ----------");
        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }
    }

    
    public static void searchByName(Scanner sc, Product[] products, int productCount) {
        System.out.print("請輸入要搜尋的商品名稱：");
        String keyword = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(keyword)) {
                System.out.println("找到商品：" + products[i].toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到名稱為「" + keyword + "」的商品。");
        }
    }

    
    public static boolean isDuplicateName(Product[] products, int productCount, String name) {
        String trimmedName = name.trim();
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(trimmedName)) {
                return true;
            }
        }
        return false;
    }

   
    public static boolean addProduct(Scanner sc, Product[] products, int productCount) {
        if (productCount >= products.length) {
            System.out.println("陣列已滿，無法新增商品。");
            return false;
        }

        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();

        if (isDuplicateName(products, productCount, name)) {
            System.out.println("新增失敗，商品名稱重複。");
            return false;
        }

        System.out.print("請輸入商品價格：");
        int price = readInt(sc);

        System.out.print("請輸入商品庫存數量：");
        int stock = readInt(sc);

        products[productCount] = new Product(name, price, stock);
        System.out.println("新增成功：" + products[productCount].toString());
        return true;
    }

    
    public static boolean sellProduct(Scanner sc, Product[] products, int productCount) {
        int index = findProductIndex(sc, products, productCount);
        if (index == -1) {
            return false;
        }

        System.out.print("請輸入出售數量：");
        int quantity = readInt(sc);

        boolean success = products[index].sell(quantity);
        if (success) {
            System.out.println("出售成功，目前庫存：" + products[index].getStock());
        } else {
            System.out.println("出售失敗，數量不足或輸入錯誤。");
        }
        return success;
    }

   
    public static boolean restockProduct(Scanner sc, Product[] products, int productCount) {
        int index = findProductIndex(sc, products, productCount);
        if (index == -1) {
            return false;
        }

        System.out.print("請輸入補充數量：");
        int quantity = readInt(sc);

        boolean success = products[index].restock(quantity);
        if (success) {
            System.out.println("補充成功，目前庫存：" + products[index].getStock());
        } else {
            System.out.println("補充失敗，數量必須大於 0。");
        }
        return success;
    }

    
    public static boolean modifyPrice(Scanner sc, Product[] products, int productCount) {
        int index = findProductIndex(sc, products, productCount);
        if (index == -1) {
            return false;
        }

        System.out.print("請輸入新價格：");
        int newPrice = readInt(sc);

        boolean success = products[index].setPrice(newPrice);
        if (success) {
            System.out.println("修改成功，新價格：" + products[index].getPrice());
        } else {
            System.out.println("修改失敗，價格必須大於 0。");
        }
        return success;
    }

    
    public static void displayLowStock(Product[] products, int productCount) {
        System.out.println("---------- 低庫存商品 ----------");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i].toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    
    public static void displayTotalInventoryValue(Product[] products, int productCount) {
        long totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            totalValue += products[i].getInventoryValue();
        }
        System.out.println("目前全部庫存總價值為：" + totalValue);
    }

    
    public static void displaySummary(int addCount, int sellCount, int restockCount,
      int priceChangeCount, int searchCount) {
        System.out.println("---------- 操作摘要 ----------");
        System.out.println("新增商品次數：" + addCount);
        System.out.println("出售商品次數：" + sellCount);
        System.out.println("補充庫存次數：" + restockCount);
        System.out.println("修改價格次數：" + priceChangeCount);
        System.out.println("搜尋商品次數：" + searchCount);
        System.out.println("感謝使用商品管理系統，再見！");
    }

   
    public static int findProductIndex(Scanner sc, Product[] products, int productCount) {
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine().trim();

        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }

        System.out.println("找不到名稱為「" + name + "」的商品。");
        return -1;
    }

   
    public static int readInt(Scanner sc) {
        String input = sc.nextLine();
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("輸入格式錯誤，視為 -1 處理。");
            return -1;
        }
    }
}
