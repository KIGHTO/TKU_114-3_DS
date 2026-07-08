import java.util.*;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名: ");
        String name = sc.next();

        System.out.print("請輸入 Java 成績: ");
        double javaScore = sc.nextDouble();

        System.out.print("請輸入 English 成績: ");
        double englishScore = sc.nextDouble();

        System.out.print("請輸入 Math 成績: ");
        double mathScore = sc.nextDouble();

        double average = (javaScore + englishScore + mathScore) / 3;

        boolean pass = average >= 60;

        String grade;
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int option = -1;//不可為0，要給一個不為0的初始值

        while (option != 0) {
            System.out.println("\n===== 選單 =====");
            System.out.println("1: 顯示平均分數");
            System.out.println("2: 顯示及格狀態");
            System.out.println("3: 顯示等第");
            System.out.println("0: 離開");
            System.out.print("請選擇: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("姓名: " + name + "，平均分數: " + average);
                    break;
                case 2:
                    String status;
                         if (pass) {
                            status = "及格";
                         } else {
                             status = "不及格";
                         }
                         System.out.println("姓名: " + name + "，及格狀態: " + status);
                         break;
                case 3:
                    System.out.println("姓名: " + name + "，等第: " + grade);
                    break;
                case 0:
                    System.out.println("End");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入");
            }
        }

        sc.close();
    }
}
