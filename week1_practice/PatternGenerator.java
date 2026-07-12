import java.util.*;

public class PatternGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;

                case 2:
                    int triangleHeight = readPositiveInt(sc, "請輸入高度: ");
                    printTriangle(triangleHeight);
                    break;

                case 3:
                    int reverseHeight = readPositiveInt(sc, "請輸入高度: ");
                    printReverseTriangle(reverseHeight);
                    break;

                case 4:
                    int rows = readPositiveInt(sc, "請輸入列數: ");
                    int cols = readPositiveInt(sc, "請輸入欄數: ");
                    printNumberGrid(rows, cols);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Pattern Menu ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(message);
            value = sc.nextInt();

            if (value > 0) {
                valid = true;
            } else {
                System.out.println("數值必須大於 0，請重新輸入。");
            }
        }

        return value;
    }

    public static void printMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(i + "x" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
