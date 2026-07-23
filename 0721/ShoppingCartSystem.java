import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    private static ArrayList<CartItem> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        do {
            showMenu();
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        updateItemQuantity();
                        break;
                    case 3:
                        removeItem();
                        break;
                    case 4:
                        viewCartAndTotal();
                        break;
                    case 5:
                        System.out.println("感謝使用購物車系統！");
                        break;
                    default:
                        System.out.println("錯誤：無效的選項，請重新輸入代碼 (1-5)。");
                }
            } catch (NumberFormatException e) {
                System.out.println("錯誤：請輸入有效的數字選項！");
            }
            System.out.println();
        } while (choice != 5);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("===== 購物車系統 =====");
        System.out.println("1. 加入商品 / 新增數量");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 檢視購物車與計算總額");
        System.out.println("5. 離開系統");
        System.out.print("請選擇功能 (1-5): ");
    }

    public static int findItemIndexById(String id) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static void addItem() {
        System.out.print("請輸入商品代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("錯誤：商品代碼不得為空白！");
            return;
        }

        System.out.print("請輸入商品名稱: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("錯誤：商品名稱不得為空白！");
            return;
        }

        System.out.print("請輸入商品單價: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
            if (price < 0) {
                System.out.println("錯誤：單價不可為負數！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：請輸入有效的數字單價！");
            return;
        }

        System.out.print("請輸入購買數量: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("錯誤：數量小於或等於 0 時不接受更新/新增！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：請輸入有效的整數數量！");
            return;
        }

        int index = findItemIndexById(id);
        if (index != -1) {
            // 重複加入相同代碼時增加數量，不建立重複物件
            CartItem existingItem = cart.get(index);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            System.out.println("提示：商品已存在於購物車中，已自動幫您增加數量！");
        } else {
            cart.add(new CartItem(id, name, price, quantity));
            System.out.println("成功：商品已加入購物車！");
        }
    }

    public static void updateItemQuantity() {
        if (cart.isEmpty()) {
            System.out.println("目前購物車為空。");
            return;
        }

        System.out.print("請輸入要修改數量的商品代碼: ");
        String id = scanner.nextLine().trim();
        int index = findItemIndexById(id);

        if (index == -1) {
            System.out.println("錯誤：找不到該代碼的商品！");
            return;
        }

        System.out.print("請輸入新的數量: ");
        try {
            int newQuantity = Integer.parseInt(scanner.nextLine().trim());
            if (newQuantity <= 0) {
                System.out.println("錯誤：數量小於或等於 0 時不接受更新！");
                return;
            }
            cart.get(index).setQuantity(newQuantity);
            System.out.println("成功：商品數量已更新！");
        } catch (NumberFormatException e) {
            System.out.println("錯誤：請輸入有效的整數數量！");
        }
    }

    public static void removeItem() {
        if (cart.isEmpty()) {
            System.out.println("目前購物車為空。");
            return;
        }

        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();
        int index = findItemIndexById(id);

        if (index == -1) {
            System.out.println("錯誤：找不到該代碼的商品！");
            return;
        }

        cart.remove(index);
        System.out.println("成功：商品已從購物車移除！");
    }

    public static void viewCartAndTotal() {
        if (cart.isEmpty()) {
            System.out.println("目前購物車為空。");
            return;
        }

        System.out.println("===== 購物車清單 =====");
        double totalAmount = 0;
        for (CartItem item : cart) {
            item.displayItem();
            totalAmount += item.getTotalPrice();
        }
        System.out.println("---------------------");
        System.out.println("購物車總金額: " + totalAmount);
    }
}
