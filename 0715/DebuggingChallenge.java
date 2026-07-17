/*
 * 錯誤 1：
 * 錯誤位置：if (command == "exit") 區塊內的 System.out.println(...) 敘述
 * 錯誤類型：編譯錯誤
 * 原因：該行結尾少了分號，導致程式無法通過編譯
 * 修正方式：在 System.out.println("系統結束，年齡：" + age) 的後面補上分號
 *
 * 錯誤 2：
 * 錯誤位置：for (int i = 0; i <= scores.length; i++)
 * 錯誤類型：陣列越界錯誤（ArrayIndexOutOfBoundsException）
 * 原因：迴圈條件使用 i <= scores.length，當 i 等於 scores.length 時會存取超出陣列範圍的索引
 * 修正方式：迴圈條件改為 i < scores.length
 *
 * 錯誤 3：
 * 錯誤位置：if (command == "exit")
 * 錯誤類型：字串比較錯誤
 * 原因：使用 == 比較字串是比較物件參考位址，而不是比較實際內容，可能導致比對失敗
 * 修正方式：改用 command.equals("exit")
 *
 * 錯誤 4：
 * 錯誤位置：double average = total / scores.length;
 * 錯誤類型：整數除法造成的邏輯錯誤
 * 原因：total 與 scores.length 都是 int，相除時會先做整數除法、捨去小數，才轉成 double
 * 修正方式：將其中一個運算元轉型為 double，改為 (double) total / scores.length
 *
 * 錯誤 5：
 * 錯誤位置：int age = sc.nextInt(); 之後接著 String command = sc.nextLine();
 * 錯誤類型：Scanner 換行問題
 * 原因：nextInt() 只會讀取數字本身，不會讀取該行結尾的換行字元，導致緊接著的 nextLine() 讀到的是殘留的空字串，而不是使用者輸入的指令
 * 修正方式：在 nextInt() 之後、讀取指令之前，先多呼叫一次 sc.nextLine() 清掉殘留的換行字元
 *
 * 錯誤 6：
 * 除錯記錄方式：使用 breakpoint 中斷點記錄重要變數值
 * 中斷點位置 1：total += scores[i]; 這一行，觀察每次迴圈的 i 與 total，確認迴圈在 i = 3 時已經超出陣列範圍（原始錯誤條件 i <= scores.length）
 * 中斷點位置 2：double average = ...; 這一行，觀察 total 與 scores.length 相除「前」的型別皆為 int，確認相除當下已被截斷成整數
 * 中斷點位置 3：if (command == "exit") 這一行，觀察 command 實際讀入的內容，確認因換行殘留而讀到空字串，導致比對永遠不成立
 */

import java.util.*;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if (command.equals("exit")) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}