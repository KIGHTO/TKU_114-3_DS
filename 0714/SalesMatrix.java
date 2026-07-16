import java.util.*;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        displayTable(sales);

        System.out.println("=== 每項商品的銷售總量 ===");
        for (int p = 0; p < sales.length; p++) {
            int productTotal = calculateProductTotal(sales, p);
            System.out.println("商品 " + (p + 1) + " 總銷售量：" + productTotal);
        }

        System.out.println("=== 每天全部商品的銷售總量 ===");
        for (int d = 0; d < sales[0].length; d++) {
            int dayTotal = calculateDayTotal(sales, d);
            System.out.println("第 " + (d + 1) + " 天總銷售量：" + dayTotal);
        }

        int bestProduct = findBestProduct(sales);
        System.out.println("總銷售量最高的商品是：商品 " + (bestProduct + 1));

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int p = 0; p < sales.length; p++) {
            for (int d = 0; d < sales[p].length; d++) {
                int value;
                while (true) {
                    System.out.print("請輸入商品 " + (p + 1) + " 第 " + (d + 1) + " 天的銷售量：");
                    value = sc.nextInt();
                    if (value >= 0) {
                        break;
                    } else {
                        System.out.println("輸入錯誤，銷售量不得小於 0，請重新輸入");
                    }
                }
                sales[p][d] = value;
            }
        }
    }

    public static void displayTable(int[][] sales) {
        System.out.println("=== 銷售量表 ===");
        System.out.print("        ");
        for (int d = 0; d < sales[0].length; d++) {
            System.out.print("第" + (d + 1) + "天\t");
        }
        System.out.println();

        for (int p = 0; p < sales.length; p++) {
            System.out.print("商品" + (p + 1) + "\t");
            for (int d = 0; d < sales[p].length; d++) {
                System.out.print(sales[p][d] + "\t");
            }
            System.out.println();
        }
    }

    public static int calculateProductTotal(int[][] sales, int productIndex) {
        int total = 0;
        for (int d = 0; d < sales[productIndex].length; d++) {
            total = total + sales[productIndex][d];
        }
        return total;
    }

    public static int calculateDayTotal(int[][] sales, int dayIndex) {
        int total = 0;
        for (int p = 0; p < sales.length; p++) {
            total = total + sales[p][dayIndex];
        }
        return total;
    }

    public static int findBestProduct(int[][] sales) {
        int bestIndex = 0;
        int bestTotal = calculateProductTotal(sales, 0);

        for (int p = 1; p < sales.length; p++) {
            int currentTotal = calculateProductTotal(sales, p);
            if (currentTotal > bestTotal) {
                bestTotal = currentTotal;
                bestIndex = p;
            }
        }
        return bestIndex;
    }
}
