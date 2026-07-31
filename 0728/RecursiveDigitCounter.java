import java.util.Scanner;

public class RecursiveDigitCounter {
   

    public static int countDigit(int number, int target) {
        if (number < 10) {
            if (number == target) {
                return 1;
            } else {
                return 0;
            }
        } else {
            int lastDigit = number % 10;

            if (lastDigit == target) {
                return 1 + countDigit(number / 10, target);
            } else {
                return countDigit(number / 10, target);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("內建測試資料結果：");
        System.out.println("countDigit(122333, 3) = " + countDigit(122333, 3));
        System.out.println("countDigit(1000, 0) = " + countDigit(1000, 0));
        System.out.println("countDigit(555555, 5) = " + countDigit(555555, 5));
        System.out.println("countDigit(123456, 9) = " + countDigit(123456, 9));
        System.out.println("countDigit(0, 0) = " + countDigit(0, 0));
        System.out.println("countDigit(90909, 9) = " + countDigit(90909, 9));

        while (true) {
            System.out.println("");
            System.out.println("請輸入要統計的數字（輸入 -1 結束）：");
            String numberInput = sc.nextLine();
            int number = Integer.parseInt(numberInput);

            if (number == -1) {
                System.out.println("程式結束");
                break;
            }

            System.out.println("請輸入要統計的目標數字（0 到 9）：");
            String targetInput = sc.nextLine();
            int target = Integer.parseInt(targetInput);

            if (target < 0 || target > 9) {
                System.out.println("目標數字必須介於 0 到 9");
            } else {
                int result = countDigit(number, target);
                System.out.println("數字 " + number + " 中出現 " + target + " 的次數為 " + result);
            }
        }

        sc.close();
    }
}
