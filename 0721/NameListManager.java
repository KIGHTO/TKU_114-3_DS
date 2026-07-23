import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();

        while (true) {
            System.out.println("");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部姓名");
            System.out.println("0. 結束程式");
            System.out.println("請選擇功能:");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addName(sc, names);
            } else if (choice.equals("2")) {
                searchName(sc, names);
            } else if (choice.equals("3")) {
                modifyName(sc, names);
            } else if (choice.equals("4")) {
                deleteName(sc, names);
            } else if (choice.equals("5")) {
                listAll(names);
            } else if (choice.equals("0")) {
                System.out.println("程式結束。");
                break;
            } else {
                System.out.println("選項錯誤,請重新輸入。");
            }
        }
    }

    public static void addName(Scanner sc, ArrayList<String> names) {
        System.out.println("請輸入姓名:");
        String name = sc.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("姓名不可空白,新增失敗。");
            return;
        }

        names.add(name);
        System.out.println("新增成功。");
    }

    public static void searchName(Scanner sc, ArrayList<String> names) {
        System.out.println("請輸入要搜尋的姓名:");
        String target = sc.nextLine();
        int index = findIndex(names, target);

        if (index == -1) {
            System.out.println("找不到這個姓名。");
        } else {
            System.out.println("找到姓名:" + names.get(index));
        }
    }

    public static void modifyName(Scanner sc, ArrayList<String> names) {
        System.out.println("請輸入要修改的姓名:");
        String target = sc.nextLine();
        int index = findIndex(names, target);

        if (index == -1) {
            System.out.println("找不到這個姓名,修改失敗。");
            return;
        }

        System.out.println("請輸入新的姓名:");
        String newName = sc.nextLine();

        if (newName.trim().isEmpty()) {
            System.out.println("姓名不可空白,修改失敗。");
            return;
        }

        names.set(index, newName);
        System.out.println("修改成功。");
    }

    public static void deleteName(Scanner sc, ArrayList<String> names) {
        System.out.println("請輸入要刪除的姓名:");
        String target = sc.nextLine();
        int index = findIndex(names, target);

        if (index == -1) {
            System.out.println("找不到這個姓名,刪除失敗。");
            return;
        }

        names.remove(index);
        System.out.println("刪除成功。");
    }

    public static void listAll(ArrayList<String> names) {
        if (names.size() == 0) {
            System.out.println("目前沒有任何姓名。");
            return;
        }

        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    public static int findIndex(ArrayList<String> names, String target) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }

        return -1;
    }
}
