import java.util.*;
import java.util.Stack;

public class TextEditorUndoSystem {
    static String content = "";
    static Stack<String> historyStack = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("===== 文字編輯 Undo 系統 =====");
            System.out.println("目前內容：" + content);
            System.out.println("1. 新增文字");
            System.out.println("2. 刪除最後數個字元");
            System.out.println("3. Undo");
            System.out.println("4. 顯示內容");
            System.out.println("5. 結束程式");
            System.out.println("請選擇功能：");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addText(sc);
            } else if (choice.equals("2")) {
                deleteLastChars(sc);
            } else if (choice.equals("3")) {
                undo();
            } else if (choice.equals("4")) {
                showContent();
            } else if (choice.equals("5")) {
                System.out.println("程式結束");
                break;
            } else {
                System.out.println("輸入錯誤，請重新選擇");
            }
        }

        sc.close();
    }

    public static void addText(Scanner sc) {
        System.out.println("請輸入要新增的文字：");
        String newText = sc.nextLine();

        historyStack.push(content);
        content = content + newText;
        System.out.println("新增完成，目前內容：" + content);
    }

    public static void deleteLastChars(Scanner sc) {
        System.out.println("請輸入要刪除的字元數：");
        String input = sc.nextLine();
        int count;

        try {
            count = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("輸入格式錯誤，請輸入數字");
            return;
        }

        if (count < 0) {
            System.out.println("刪除字元數不可為負數");
            return;
        }

        historyStack.push(content);

        if (count >= content.length()) {
            content = "";
        } else {
            content = content.substring(0, content.length() - count);
        }

        System.out.println("刪除完成，目前內容：" + content);
    }

    public static void undo() {
        if (historyStack.isEmpty()) {
            System.out.println("沒有歷史紀錄可以 Undo");
            return;
        }

        content = historyStack.pop();
        System.out.println("已復原，目前內容：" + content);
    }

    public static void showContent() {
        System.out.println("目前內容：" + content);
    }
}
