import java.util.*;
import java.util.Stack;

public class BrowserUndoSystem {
    static Stack<String> historyStack = new Stack<>();
    static String currentPage = "首頁";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("===== 瀏覽操作復原系統 =====");
            System.out.println("目前頁面：" + currentPage);
            System.out.println("1. 開啟新頁面");
            System.out.println("2. 返回上一頁");
            System.out.println("3. 查看目前頁面");
            System.out.println("4. 結束程式");
            System.out.println("請選擇功能：");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                openNewPage(sc);
            } else if (choice.equals("2")) {
                goBack();
            } else if (choice.equals("3")) {
                viewCurrentPage();
            } else if (choice.equals("4")) {
                System.out.println("程式結束");
                break;
            } else {
                System.out.println("輸入錯誤，請重新選擇");
            }
        }

        sc.close();
    }

    public static void openNewPage(Scanner sc) {
        System.out.println("請輸入要開啟的頁面名稱：");
        String newPage = sc.nextLine();

        if (newPage.equals("")) {
            System.out.println("頁面名稱不可為空");
            return;
        }

        historyStack.push(currentPage);
        currentPage = newPage;
        System.out.println("已開啟頁面：" + currentPage);
    }

    public static void goBack() {
        if (historyStack.isEmpty()) {
            System.out.println("目前沒有上一頁可以返回");
            return;
        }

        currentPage = historyStack.pop();
        System.out.println("已返回頁面：" + currentPage);
    }

    public static void viewCurrentPage() {
        System.out.println("目前頁面為：" + currentPage);
    }
}
