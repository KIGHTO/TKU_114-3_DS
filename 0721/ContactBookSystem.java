import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    private static ArrayList<Contact> contactList = new ArrayList<>();
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
                        addContact();
                        break;
                    case 2:
                        searchContact();
                        break;
                    case 3:
                        updateContact();
                        break;
                    case 4:
                        deleteContact();
                        break;
                    case 5:
                        listAllContacts();
                        break;
                    case 6:
                        System.out.println("感謝使用聯絡人管理系統！");
                        break;
                    default:
                        System.out.println("錯誤：無效的選項，請重新輸入代碼 (1-6)。");
                }
            } catch (NumberFormatException e) {
                System.out.println("錯誤：請輸入有效的數字選項！");
            }
            System.out.println();
        } while (choice != 6);
        
        scanner.close();
    }

    // Custom Method 1: Show Menu
    public static void showMenu() {
        System.out.println("===== 聯絡人管理系統 =====");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 顯示完整清單");
        System.out.println("6. 離開系統");
        System.out.print("請選擇功能 (1-6): ");
    }

    // Custom Method 2: Find index by ID
    public static int findContactIndexById(String id) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    // Custom Method 3: Add Contact (Handles duplicates and empty names)
    public static void addContact() {
        System.out.print("請輸入代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("錯誤：代碼不得為空白！");
            return;
        }

        if (findContactIndexById(id) != -1) {
            System.out.println("錯誤：此代碼已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("錯誤：空白姓名不可加入！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入電子郵件: ");
        String email = scanner.nextLine().trim();

        contactList.add(new Contact(id, name, phone, email));
        System.out.println("成功：聯絡人已新增！");
    }

    // Custom Method 4: Search Contact
    public static void searchContact() {
        if (contactList.isEmpty()) {
            System.out.println("目前沒有任何聯絡人資料。");
            return;
        }

        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：搜尋關鍵字不得為空白！");
            return;
        }

        boolean found = false;
        for (Contact c : contactList) {
            if (c.getId().equals(keyword) || c.getName().contains(keyword)) {
                c.displayContact();
                found = true;
            }
        }

        if (!found) {
            System.out.println("提示：找不到符合的聯絡人。");
        }
    }

    // Custom Method 5: Update Contact Phone
    public static void updateContact() {
        if (contactList.isEmpty()) {
            System.out.println("目前沒有任何聯絡人資料可供修改。");
            return;
        }

        System.out.print("請輸入要修改的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        int index = findContactIndexById(id);

        if (index == -1) {
            System.out.println("錯誤：找不到該代碼的聯絡人！");
            return;
        }

        System.out.print("請輸入新的電話號碼: ");
        String newPhone = scanner.nextLine().trim();
        if (newPhone.isEmpty()) {
            System.out.println("錯誤：電話號碼不得為空白！"); // Fixed safety check
            // Alternatively: contactList.get(index).setPhone(newPhone);
        }
        
        contactList.get(index).setPhone(newPhone);
        System.out.println("成功：聯絡人電話已更新！");
    }

    // Custom Method 6: Delete Contact
    public static void deleteContact() {
        if (contactList.isEmpty()) {
            System.out.println("目前沒有任何聯絡人資料可供刪除。");
            return;
        }

        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        int index = findContactIndexById(id);

        if (index == -1) {
            System.out.println("錯誤：找不到該代碼的聯絡人！");
            return;
        }

        contactList.remove(index);
        System.out.println("成功：聯絡人已刪除！");
    }

    // Custom Method 7: List All Contacts
    public static void listAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("目前通訊錄為空白。");
            return;
        }

        System.out.println("===== 完整聯絡人清單 =====");
        for (Contact c : contactList) {
            c.displayContact();
        }
    }
}
