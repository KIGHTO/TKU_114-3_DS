import java.util.Scanner;

public class ScoreReport {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入姓名: ");

        String name = sc.nextLine();
        System.out.print("請輸入 Java 成績: ");

        double java = sc.nextDouble();
        System.out.print("請輸入 English 成績: ");

        double english = sc.nextDouble();
        System.out.print("請輸入 Math 成績: ");

        double math = sc.nextDouble();
        double average = (java + english + math) / 3.0; //計算平均分數，使用 double來保留小數點

        System.out.println("=== 成績報表 ===");
        System.out.println("姓名: " + name);
        System.out.println("Java: " + java);
        System.out.println("English: " + english);
        System.out.println("Math: " + math);
        System.out.println("平均: " + average); //輸出計算後的平均分數

        sc.close();
    }
}
