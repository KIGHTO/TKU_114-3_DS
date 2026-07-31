import java.util.*;
 
public class RecursiveDigitSumPractice {
    public static int digitSum(int number) {
        if (number < 10) {
            return number;
        } else {
            return number % 10 + digitSum(number / 10);
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int[] testNumbers = {5729, 0, 9, 100, 123456, 999999};
 
        System.out.println("內建測試資料結果：");
        for (int i = 0; i < testNumbers.length; i++) {
            int number = testNumbers[i];
            int result = digitSum(number);
            System.out.println("digitSum(" + number + ") = " + result);
        }
 
        while (true) {
            System.out.println("");
            System.out.println("請輸入一個整數計算各位數字和（輸入 -1 結束）：");
            String input = sc.nextLine();
            int number = Integer.parseInt(input);
 
            if (number == -1) {
                System.out.println("程式結束");
                break;
            }
 
            if (number < 0) {
                System.out.println("請輸入 0 或正整數");
            } else {
                int result = digitSum(number);
                System.out.println("digitSum(" + number + ") = " + result);
            }
        }
 
        sc.close();
    }
}
 