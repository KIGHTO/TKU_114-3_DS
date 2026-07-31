import java.util.Scanner;

public class RecursiveNameSearchPractice {
  

    public static int search(String[] names, String target, int index) {
        if (index >= names.length) {
            return -1;
        } else if (names[index].equals(target)) {
            return index;
        } else {
            return search(names, target, index + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Amy", "Bob", "Cindy", "David", "Ellen", "Frank", "Grace", "Henry"};
        String[] emptyNames = {};

        System.out.println("內建測試資料結果：");
        System.out.println("search(names, \"Amy\", 0) = " + search(names, "Amy", 0));
        System.out.println("search(names, \"Henry\", 0) = " + search(names, "Henry", 0));
        System.out.println("search(names, \"Cindy\", 0) = " + search(names, "Cindy", 0));
        System.out.println("search(names, \"Zoe\", 0) = " + search(names, "Zoe", 0));
        System.out.println("search(emptyNames, \"Amy\", 0) = " + search(emptyNames, "Amy", 0));
        System.out.println("search(names, \"David\", 0) = " + search(names, "David", 0));

        while (true) {
            System.out.println("");
            System.out.println("請輸入要搜尋的姓名（輸入 exit 結束）：");
            String target = sc.nextLine();

            if (target.equals("exit")) {
                System.out.println("程式結束");
                break;
            }

            int index = search(names, target, 0);

            if (index == -1) {
                System.out.println("找不到姓名 " + target);
            } else {
                System.out.println("找到姓名 " + target + "，索引為 " + index);
            }
        }

        sc.close();
    }
}
